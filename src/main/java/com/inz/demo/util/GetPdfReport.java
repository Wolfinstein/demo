package com.inz.demo.util;

import com.inz.demo.domain.Grade;
import com.inz.demo.domain.Subject;
import com.inz.demo.domain.User;
import com.inz.demo.repository.*;
import com.inz.demo.service.IUserService;
import com.inz.demo.service.impl.UserServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class GetPdfReport {

    private Logger logger = LoggerFactory.getLogger(GetPdfReport.class);


    private final IUserService userService;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final PresenceRepository presenceRepository;
    private final ClassRepository classRepository;

    public GetPdfReport(UserServiceImpl userService, SubjectRepository subjectRepository, UserRepository userRepository, GradeRepository gradeRepository, PresenceRepository presenceRepository, ClassRepository classRepository) {
        this.userService = userService;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.gradeRepository = gradeRepository;
        this.presenceRepository = presenceRepository;
        this.classRepository = classRepository;
    }

    public ByteArrayInputStream studentReport(Long studentId) throws NullPointerException {

        Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            User student = userService.findUserById(studentId).get();
            List<Subject> subjectList = subjectRepository.findBySubjectClassClassId(student.getUserClass().getClassId());

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25f);
            Paragraph paragraph = new Paragraph("Student Report", titleFont);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            paragraph.setAlignment(Element.ALIGN_CENTER);
            PdfPTable table = new PdfPTable(3);
            table.setSpacingAfter(30f);
            table.setSpacingBefore(40f);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{3, 3, 3});

            PdfPCell cell;
            PdfPCell hcell;

            //informations
            hcell = new PdfPCell(new Phrase("Full Name: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Class: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Preceptor: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            cell = new PdfPCell(new Phrase(student.getUserName() + " " + student.getUserSurname()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(student.getUserClass().getClassSign() + " / " + student.getUserClass().getClassYear()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(student.getUserClass().getPreceptor().getUserName() + " " + student.getUserClass().getPreceptor().getUserSurname()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingRight(5);
            table.addCell(cell);

            //grades
            PdfPTable tableGrade = new PdfPTable(4);
            tableGrade.setSpacingAfter(30f);
            tableGrade.setWidthPercentage(80);
            tableGrade.setWidths(new int[]{2, 2, 4, 1});


            hcell = new PdfPCell(new Phrase("Subject Name: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableGrade.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Teacher: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableGrade.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Grades: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableGrade.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Average : ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableGrade.addCell(hcell);

            // grades
            double allAverage = 0.0;
            int counter = 0;
            for (Subject s : subjectList) {
                cell = new PdfPCell(new Phrase(s.getName()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableGrade.addCell(cell);

                cell = new PdfPCell(new Phrase(s.getTeacher().getUserName() + s.getTeacher().getUserSurname()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableGrade.addCell(cell);

                List<Grade> gradesList = gradeRepository.findByStudent_UserIdAndSubject_SubjectId(studentId, s.getSubjectId());
                StringBuilder grades = new StringBuilder();
                for (Grade grade : gradesList) {
                    grades.append(grade.getGradeValue().toString()).append(", ");
                }

                OptionalDouble average = gradesList
                        .stream()
                        .mapToDouble(Grade::getGradeValue)
                        .average();

                allAverage += average.orElse(0);
                if (average.orElse(0) != 0) {
                    counter++;
                }

                cell = new PdfPCell(new Phrase(grades.toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableGrade.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(average.orElse(0))));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                tableGrade.addCell(cell);
            }

            cell = new PdfPCell(new Phrase("---"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingRight(5);
            tableGrade.addCell(cell);

            cell = new PdfPCell(new Phrase("---"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingRight(5);
            tableGrade.addCell(cell);

            cell = new PdfPCell(new Phrase("---"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingRight(5);
            tableGrade.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(allAverage / counter)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingRight(5);
            tableGrade.addCell(cell);

            //presences
            PdfPTable presenceTable = new PdfPTable(5);
            presenceTable.setWidthPercentage(80);
            presenceTable.setWidths(new int[]{3, 3, 3, 3, 3});

            hcell = new PdfPCell(new Phrase("All: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(hcell);

            hcell = new PdfPCell(new Phrase("PRESENT: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(hcell);

            hcell = new PdfPCell(new Phrase("ABSENT: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(hcell);

            hcell = new PdfPCell(new Phrase("JUSTIFIED : ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(hcell);

            hcell = new PdfPCell(new Phrase("LATE : ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(hcell);

            cell = new PdfPCell(new Phrase(String.valueOf(presenceRepository.findByStudent_UserId(studentId).size())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(presenceRepository.findByStudent_UserIdAndType(studentId, "PRESENT").size())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(presenceRepository.findByStudent_UserIdAndType(studentId, "ABSENT").size())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(presenceRepository.findByStudent_UserIdAndType(studentId, "JUSTIFIED").size())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(presenceRepository.findByStudent_UserIdAndType(studentId, "LATE").size())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            presenceTable.addCell(cell);

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(paragraph);
            document.addTitle("Student report");
            document.add(table);
            document.add(tableGrade);
            document.add(presenceTable);

            document.close();

        } catch (DocumentException ex) {
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


    public ByteArrayInputStream teacherReport(Long subjectId) throws NullPointerException {

        Document document = new Document(PageSize.A4, 10f, 10f, 10f, 0f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Subject subject = subjectRepository.getOne(subjectId);
            List<User> students = userRepository
                    .findAllByUserClassIsNotNull()
                    .stream()
                    .filter(user -> user.getUserClass() == subject.getSubjectClass())
                    .collect(Collectors.toList());

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25f);
            Paragraph paragraph = new Paragraph(subject.getName(), titleFont);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            paragraph.setAlignment(Element.ALIGN_CENTER);
            PdfPTable table = new PdfPTable(2);
            table.setSpacingAfter(30f);
            table.setSpacingBefore(40f);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{5, 5});

            PdfPCell cell;
            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Full Name: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Grades: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (User u : students) {

                cell = new PdfPCell(new Phrase(u.getUserName() + " " + u.getUserSurname()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                List<Grade> gradesList = gradeRepository.findByStudent_UserIdAndSubject_SubjectId(u.getUserId(), subject.getSubjectId());
                StringBuilder grades = new StringBuilder();
                for (Grade grade : gradesList) {
                    grades.append(grade.getGradeValue().toString()).append(", ");
                }

                cell = new PdfPCell(new Phrase(grades.toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(paragraph);
            document.addTitle(subject.getName());
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


}