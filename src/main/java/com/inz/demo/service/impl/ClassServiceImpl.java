package com.inz.demo.service.impl;

import com.inz.demo.domain.Class;
import com.inz.demo.domain.Subject;
import com.inz.demo.domain.User;
import com.inz.demo.repository.ClassRepository;
import com.inz.demo.repository.SubjectRepository;
import com.inz.demo.repository.UserRepository;
import com.inz.demo.service.IClassService;
import com.inz.demo.util.DTOs.ClassDTO;
import com.inz.demo.util.DTOs.SubjectDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.inz.demo.util.methods.SplitIntegers.splitStringToIntArrays;

@Service
public class ClassServiceImpl implements IClassService {

    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    public ClassServiceImpl(UserRepository userRepository, ClassRepository classRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
    }


    @Override
    public void createClass(ClassDTO classDTO) {
        Class _class = Class.builder()
                .classSign(classDTO.getClassSign())
                .classYear(classDTO.getClassYear())
                .preceptor(userRepository.findByUserId(classDTO.getPreceptorId()))
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
        classRepository.deleteById(id);
    }

    @Override
    public Optional<Class> findClassById(Long id) {
        return classRepository.findByClassId(id);
    }

    @Override
    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Subject> findSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void changePreceptor(Long preceptorId, Long classId) {
        Class _class = classRepository.findByClassId(classId).get();
        _class.setPreceptor(userRepository.findByUserId(preceptorId));
        classRepository.save(_class);
    }

    @Override
    public void changeTeacher(Long teacherId, Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).get();
        subject.setTeacher(userRepository.findByUserId(teacherId));
        subjectRepository.save(subject);
    }


}
