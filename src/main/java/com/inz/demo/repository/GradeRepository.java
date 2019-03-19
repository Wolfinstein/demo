package com.inz.demo.repository;

import com.inz.demo.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByGradeId(Long id);

    List<Grade> findByStudent_UserIdAndSubject_SubjectId(Long studentId, Long subjectId);

}
