package com.inz.demo.service.impl;

import com.inz.demo.domain.*;
import com.inz.demo.domain.Class;
import com.inz.demo.repository.*;
import com.inz.demo.service.IClassService;
import com.inz.demo.util.DTOs.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.inz.demo.util.methods.SplitIntegers.splitStringToIntArrays;

@Service
public class ClassServiceImpl implements IClassService {

    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final GradeRepository gradeRepository;
    private final PresenceRepository presenceRepository;
    private final UserKidRepository userKidRepository;

    public ClassServiceImpl(UserRepository userRepository, ClassRepository classRepository, SubjectRepository subjectRepository, LessonRepository lessonRepository, GradeRepository gradeRepository, PresenceRepository presenceRepository, UserKidRepository userKidRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.gradeRepository = gradeRepository;
        this.presenceRepository = presenceRepository;
        this.userKidRepository = userKidRepository;
    }

    @Override
    public void createClass() {
        Class _class = Class.builder()
                .classSign("-")
                .classYear(0)
                .build();

        classRepository.save(_class);
    }

    @Override
    public void addStudents(String studentIds, Long classId) {
        Class _class = classRepository.findByClassId(classId).get();
        int[] arr = splitStringToIntArrays(studentIds);
        List<User> students = new ArrayList<>();
        for (int _int : arr) {
            students.add(userRepository.findByUserId((long) _int));
        }
        _class.setUsers(students);
        classRepository.save(_class);
    }

    @Override
    public void addSubject(SubjectDTO subjectDTO, Long classId) {
        Class _class = classRepository.findByClassId(classId).get();
        Subject subject = Subject.builder()
                .name(subjectDTO.getName())
                .teacher(userRepository.findByUserId(subjectDTO.getTeacherId()))
                .subjectTimeStart(subjectDTO.getSubjectTimeStart())
                .build();
        List<Subject> subjects = _class.getSubjects();
        subjects.add(subject);
        _class.setSubjects(subjects);
        classRepository.save(_class);
    }

    @Override
    public Optional<Class> findClassBySign(String sign) {
        return classRepository.findByClassSign(sign);
    }

    @Override
    public void deleteClass(Long id) {
        Class _class = classRepository.getOne(id);

        if (_class.getUsers().isEmpty() && _class.getSubjects().isEmpty()) {
            classRepository.deleteById(id);

        }
    }

    @Override
    public Optional<Class> findClassById(Long id) {
        return classRepository.findByClassId(id);
    }

    @Override
    public List<ClassListDTO> getClasses() {
        List<Class> classes = classRepository.findAll();
        List<ClassListDTO> dtos = new ArrayList<>();

        for (Class c : classes) {
            ClassListDTO dto = ClassListDTO.builder()
                    .id(c.getClassId())
                    .sign(c.getClassSign())
                    .studentsAmount(userRepository.findAllByUserClassIsNotNull().stream().filter(user -> user.getUserClass().getClassId().equals(c.getClassId())).collect(Collectors.toList()).size())
                    .subjectsAmount(subjectRepository.findAll().stream().filter(subject -> subject.getSubjectClass().getClassId().equals(c.getClassId())).collect(Collectors.toList()).size())
                    .year(c.getClassYear())
                    .build();

            if (c.getPreceptor() != null) {
                dto.setFullName(c.getPreceptor().getUserName() + ' ' + c.getPreceptor().getUserSurname());
                dto.setPreceptorId(c.getPreceptor().getUserId());
            } else {
                dto.setFullName("");
                dto.setPreceptorId(0L);
            }
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void changeClass(Long id, ClassDTO dto) {
        Class _class = classRepository.getOne(id);
        _class.setPreceptor(userRepository.getOne(dto.getPreceptorId()));
        _class.setClassSign(dto.getSign());
        _class.setClassYear(dto.getYear());
        classRepository.save(_class);
    }

    @Override
    public void changeTeacher(Long teacherId, Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).get();
        subject.setTeacher(userRepository.findByUserId(teacherId));
        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectListDTO> getSubjectList(Long id) {
        List<Subject> subjects = subjectRepository.findByTeacherUserId(id);
        List<SubjectListDTO> dtos = new ArrayList<>();
        for (Subject s : subjects) {
            dtos.add(SubjectListDTO.builder()
                    .id(s.getSubjectId())
                    .classSign(s.getSubjectClass().getClassSign())
                    .classStart(s.getSubjectTimeStart())
                    .classYear(s.getSubjectClass().getClassYear())
                    .name(s.getName())
                    .lessonCounter(s.getLessons().size())
                    .kidsCounter(s.getSubjectClass().getUsers().size())
                    .build());
        }
        return dtos;
    }

    @Override
    public List<LessonListDTO> getLessonsList(Long id) {
        List<LessonListDTO> dtos = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.findBySubjectSubjectId(id);
        for (Lesson l : lessons) {
            dtos.add(LessonListDTO.builder()
                    .date(l.getDate())
                    .subjectName(l.getSubject().getName())
                    .topic(l.getTopic())
                    .id(l.getLessonId())
                    .build());
        }
        return dtos;
    }

    @Override
    public List<StudentWithGradeDTO> getStudentListBySubjectId(Long id) {
        List<StudentWithGradeDTO> dtos = new ArrayList<>();
        Subject subject = subjectRepository.getOne(id);

        for (User u : subject.getSubjectClass().getUsers()) {
            dtos.add(StudentWithGradeDTO.builder()
                    .name(u.getUserName())
                    .studentId(u.getUserId())
                    .surname(u.getUserSurname())
                    .grades(gradeRepository.findByStudent_UserIdAndSubject_SubjectId(u.getUserId(), subject.getSubjectId()))
                    .average(String.valueOf(gradeRepository.findByStudent_UserIdAndSubject_SubjectId(u.getUserId(), subject.getSubjectId()).stream()
                            .mapToDouble(Grade::getGradeValue)
                            .average().orElse(0))
                    )
                    .build());
        }
        return dtos;
    }

    @Override
    public List<StudentViewDTO> getStudentByStudentId(Long id) {
        User user = userRepository.findByUserId(id);
        List<Subject> subjects = subjectRepository.findBySubjectClassClassId(user.getUserClass().getClassId());
        List<StudentViewDTO> dtos = new ArrayList<>();


        for (Subject s : subjects) {

            int absences = presenceRepository.findByStudent_UserIdAndType(id, "ABSENT").stream()
                    .filter(a -> a.getLesson().getSubject() == s)
                    .collect(Collectors.toList())
                    .size();

            int all = presenceRepository.findByStudent_UserId(id).stream()
                    .filter(a -> a.getLesson().getSubject() == s)
                    .collect(Collectors.toList())
                    .size();

            double percentage = 1 - ((double) absences / all);
            String percente = percentage + "%";


            dtos.add(StudentViewDTO.builder()
                    .fullName(user.getUserName() + " " + user.getUserSurname())
                    .subjectName(s.getName())
                    .subjectTeacher(s.getTeacher().getUserName() + " " + s.getTeacher().getUserSurname())
                    .grades(gradeRepository.findByStudent_UserIdAndSubject_SubjectId(id, s.getSubjectId()))
                    .average(String.valueOf(gradeRepository.findByStudent_UserIdAndSubject_SubjectId(id, s.getSubjectId()).stream()
                            .mapToDouble(Grade::getGradeValue)
                            .average().orElse(0))
                    )
                    .percentageOfPresences(percente)
                    .build());
        }
        return dtos;
    }

    @Override
    public List<StudentAbsencesDTO> getStudentAbsences(Long id) {
        List<Presence> absences = presenceRepository.findByStudent_UserIdAndType(id, "ABSENT");
        List<StudentAbsencesDTO> dtos = new ArrayList<>();

        for (Presence a : absences) {
            dtos.add(StudentAbsencesDTO.builder()
                    .date(a.getDate())
                    .lessonTopic(a.getLesson().getTopic())
                    .subjectName(a.getLesson().getSubject().getName())
                    .teacher(a.getTeacher().getUserName() + " " + a.getTeacher().getUserSurname())
                    .fullName(a.getStudent().getUserName() + " " + a.getStudent().getUserSurname())
                    .build());
        }

        return dtos;
    }

    @Override
    public List<List<StudentViewDTO>> getStudentsForParent(Long id) {
        List<UserKid> kids = userKidRepository.findByUser_UserId(id);
        List<List<StudentViewDTO>> dtos = new ArrayList<>(new ArrayList<>());
        for (UserKid u : kids) {
            dtos.add(getStudentByStudentId(u.getKid().getUserId()));
        }
        return dtos;
    }

    @Override
    public List<List<StudentAbsencesDTO>> getStudentAbsencesForParent(Long id) {
        List<UserKid> kids = userKidRepository.findByUser_UserId(id);
        List<List<StudentAbsencesDTO>> dtos = new ArrayList<>(new ArrayList<>());
        for (UserKid u : kids) {
            dtos.add(getStudentAbsences(u.getKid().getUserId()));
        }
        return dtos;
    }

    @Override
    public List<User> getTeachers() {
        return userRepository.findAll().stream().filter(user -> user.getRoles().equals("TEACHER")).collect(Collectors.toList());
    }


}
