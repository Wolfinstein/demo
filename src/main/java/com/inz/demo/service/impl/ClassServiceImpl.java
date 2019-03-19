package com.inz.demo.service.impl;

import com.inz.demo.domain.Class;
import com.inz.demo.domain.*;
import com.inz.demo.repository.*;
import com.inz.demo.service.IClassService;
import com.inz.demo.util.DTOs.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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
    public void addSubject(AddSubjectDTO subjectDTO, Long classId) {
        Subject subject = Subject.builder()
                .name(subjectDTO.getName())
                .subjectDay(subjectDTO.getDay())
                .subjectHour(subjectDTO.getHour())
                .teacher(userRepository.findByUserId(subjectDTO.getTeacherId()))
                .subjectClass(classRepository.findByClassId(classId).get())
                .build();
        subjectRepository.save(subject);
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
                    .classStart(String.valueOf(s.getSubjectDay()) + " / " + s.getSubjectHour())
                    .classId(s.getSubjectClass().getClassId())
                    .classDay(s.getSubjectDay())
                    .classHour(s.getSubjectHour())
                    .classYear(s.getSubjectClass().getClassYear())
                    .name(s.getName())
                    .teacherId(s.getTeacher().getUserId())
                    .lessonCounter(s.getLessons().size())
                    .kidsCounter(s.getSubjectClass().getUsers().size())
                    .build());
        }
        return dtos;
    }

    @Override
    public List<SubjectListDTO> getSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectListDTO> dtos = new ArrayList<>();
        for (Subject s : subjects) {
            dtos.add(SubjectListDTO.builder()
                    .id(s.getSubjectId())
                    .classSign(s.getSubjectClass().getClassSign())
                    .classId(s.getSubjectClass().getClassId())
                    .classDay(s.getSubjectDay())
                    .classHour(s.getSubjectHour())
                    .classYear(s.getSubjectClass().getClassYear())
                    .name(s.getName())
                    .teacherId(s.getTeacher().getUserId())
                    .lessonCounter(s.getLessons().size())
                    .kidsCounter(s.getSubjectClass().getUsers().size())
                    .teacherName(s.getTeacher().getUserName() + " " + s.getTeacher().getUserSurname())
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
                    .id(user.getUserId())
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

    @Override
    public Map<Integer, List<Subject>> getClassSchedule(Long id) {
        Map<Integer, List<Subject>> map = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            List<Subject> subjects = subjectRepository.findBySubjectClassClassIdAndSubjectDay(id, i);
            List<Subject> filtered = new ArrayList<>();
            for (int j = 8; j <= 15; j++) {
                final int w = j;
                if (subjects.stream().filter(subject -> subject.getSubjectHour() == w).collect(Collectors.toList()).isEmpty()) {
                    filtered.add(Subject.builder()
                            .name("empty")
                            .subjectHour(j)
                            .subjectDay(i)
                            .teacher(null)
                            .build());
                } else {
                    filtered.add(subjects.stream().filter(subject -> subject.getSubjectHour() == w).collect(Collectors.toList()).get(0));
                }
            }
            map.put(i, filtered);
        }
        return map;
    }

    @Override
    public Optional<Subject> getSubject(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }


}
