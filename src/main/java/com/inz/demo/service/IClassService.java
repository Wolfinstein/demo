package com.inz.demo.service;

import com.inz.demo.domain.Class;
import com.inz.demo.domain.Subject;
import com.inz.demo.domain.User;
import com.inz.demo.util.DTOs.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface IClassService {
    void createClass();

    void addSubject(AddSubjectDTO subjectDTO, Long classId);

    void deleteClass(Long id);

    Optional<Class> findClassById(Long id);

    List<ClassListDTO> getClasses();

    void changeClass(Long id, ClassDTO dto);

    void changeTeacher(Long teacherId, Long subjectId);

    List<SubjectListDTO> getSubjectList(Long id);

    List<SubjectListDTO> getSubjects();

    List<LessonListDTO> getLessonsList(Long id);

    List<StudentWithGradeDTO> getStudentListBySubjectId(Long id);

    List<StudentViewDTO> getStudentByStudentId(Long id);

    List<StudentAbsencesDTO> getStudentAbsences(Long id);

    List<List<StudentViewDTO>> getStudentsForParent(Long id);

    List<List<StudentAbsencesDTO>> getStudentAbsencesForParent(Long id);

    List<User> getTeachers();

    Map<Integer, List<Subject>> getClassSchedule(Long id);

    Optional<Subject> getSubject(Long id);

    void deleteSubject(Long id);
}
