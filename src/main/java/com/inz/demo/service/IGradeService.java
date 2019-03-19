package com.inz.demo.service;

import com.inz.demo.domain.Grade;
import com.inz.demo.util.DTOs.GradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IGradeService {
    void addGrade(GradeDTO gradeDTO);

    void deleteGrade(Long gradeId);

    void changeGrade(Long gradeId, Double gradeValue);

    Optional<Grade> findGradeById(Long id);

    List<Grade> getGrades();
}
