package com.inz.demo.service;

import com.inz.demo.domain.Class;
import com.inz.demo.domain.Subject;
import com.inz.demo.domain.User;
import com.inz.demo.util.DTOs.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IClassService {
    void createClass();

    void addStudents(String studentIds, Long classId);

    void addSubject(SubjectDTO subjectDTO, Long classId);

    Optional<Class> findClassBySign(String sign);

    void deleteClass(Long id);

    Optional<Class> findClassById(Long id);

    List<ClassListDTO> getClasses();

    void changeClass(Long id, ClassDTO dto);

    void changeTeacher(Long teacherId, Long subjectId);

    List<SubjectListDTO> getSubjectList(Long id);

    List<LessonListDTO> getLessonsList(Long id);

    List<StudentWithGradeDTO> getStudentListBySubjectId(Long id);

    List<StudentViewDTO> getStudentByStudentId(Long id);

    List<StudentAbsencesDTO> getStudentAbsences(Long id);

    List<List<StudentViewDTO>> getStudentsForParent(Long id);

    List<List<StudentAbsencesDTO>> getStudentAbsencesForParent(Long id);

    List<User> getTeachers();
}
