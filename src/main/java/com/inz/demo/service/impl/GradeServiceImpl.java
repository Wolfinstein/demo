package com.inz.demo.service.impl;

import com.inz.demo.domain.Grade;
import com.inz.demo.repository.GradeRepository;
import com.inz.demo.repository.SubjectRepository;
import com.inz.demo.repository.UserRepository;
import com.inz.demo.service.IGradeService;
import com.inz.demo.service.INotificationService;
import com.inz.demo.util.DTOs.GradeDTO;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements IGradeService {

    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final INotificationService notificationService;

    public GradeServiceImpl(GradeRepository gradeRepository, UserRepository userRepository, SubjectRepository subjectRepository, NotificationServiceImpl notificationService) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void addGrade(GradeDTO gradeDTO) {


        Grade grade = Grade.builder()
                .gradeValue(gradeDTO.getGradeValue())
                .gradeTimestamp(Calendar.getInstance().getTime())
                .student(userRepository.findByUserId(gradeDTO.getStudentId()))
                .teacher(userRepository.findByUserId(gradeDTO.getTeacherId()))
                .subject(subjectRepository.getOne(gradeDTO.getSubjectId()))
                .build();

        gradeRepository.save(grade);
        notificationService.newGradeNotification(grade.getStudent(), grade);
    }

    @Override
    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    @Override
    public void changeGrade(Long gradeId, Double gradeValue) {
        Grade grade = gradeRepository.getOne(gradeId);
        grade.setGradeValue(gradeValue);
        grade.setGradeModificationDate(Calendar.getInstance().getTime());
        gradeRepository.save(grade);
        notificationService.newGradeNotification(grade.getStudent(), grade);
    }

    @Override
    public Optional<Grade> findGradeById(Long id) {
        return gradeRepository.findByGradeId(id);
    }

    @Override
    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }
}
