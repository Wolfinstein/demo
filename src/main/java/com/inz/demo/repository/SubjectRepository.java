package com.inz.demo.repository;

import com.inz.demo.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySubjectClassClassId(Long id);

    List<Subject> findByTeacherUserId(Long id);

    List<Subject> findBySubjectClassClassIdAndSubjectDay(Long id, int number);


}
