package com.inz.demo.service;

import com.inz.demo.domain.Class;
import com.inz.demo.domain.Subject;
import com.inz.demo.util.DTOs.ClassDTO;
import com.inz.demo.util.DTOs.SubjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IClassService {
    void createClass(ClassDTO classDTO);

    void addStudents(String studentIds, Long classId);

    void addSubject(SubjectDTO subjectDTO, Long classId);

    Optional<Class> findClassBySign(String sign);

    void deleteClass(Long id);

    Optional<Class> findClassById(Long id);

    List<Class> getClasses();

    Optional<Subject> findSubjectById(Long id);

    void changePreceptor(Long preceptorId, Long classId);

    void changeTeacher(Long teacherId, Long subjectId);
}
