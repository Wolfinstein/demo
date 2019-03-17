package com.inz.demo.service;

import com.inz.demo.domain.Grade;
import com.inz.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface INotificationService {
    void newGradeNotification(User student, Grade grade);
    void writtenNotification(Long userId, String message);
    void absenceNotification(User student, Long lessonId);
}
