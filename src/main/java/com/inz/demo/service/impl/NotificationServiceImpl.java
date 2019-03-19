package com.inz.demo.service.impl;

import com.inz.demo.domain.*;
import com.inz.demo.repository.LessonRepository;
import com.inz.demo.repository.NotificationRepository;
import com.inz.demo.repository.UserKidRepository;
import com.inz.demo.repository.UserRepository;
import com.inz.demo.service.INotificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final UserKidRepository userKidRepository;
    private final LessonRepository lessonRepository;

    public NotificationServiceImpl(UserRepository userRepository, NotificationRepository notificationRepository, UserKidRepository userKidRepository, LessonRepository lessonRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.userKidRepository = userKidRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void newGradeNotification(User student, Grade grade) {
        List<Notification> notifications = new ArrayList<>();
        Notification notification = Notification.builder()
                .isRead(false)
                .content("You have a new/updated grade (" + grade.getGradeValue() + ") from " + grade.getSubject().getName())
                .notificationTimestamp(Calendar.getInstance().getTime())
                .user(student)
                .build();
        notifications.add(notification);
        List<UserKid> parents = userKidRepository.findByKid_UserId(student.getUserId());

        for (UserKid parent : parents) {
            Notification parentNotification = Notification.builder()
                    .isRead(false)
                    .content("Your son/daughter has a new/updated grade (" + grade.getGradeValue() + ") from " + grade.getSubject().getName())
                    .notificationTimestamp(Calendar.getInstance().getTime())
                    .user(parent.getUser())
                    .build();
            notifications.add(parentNotification);
        }
        notificationRepository.saveAll(notifications);
    }

    @Override
    public void writtenNotification(Long userId, String message) {
        List<Notification> notifications = new ArrayList<>();
        User student = userRepository.getOne(userId);
        Notification notification = Notification.builder()
                .isRead(false)
                .content("You have a new message :" + message)
                .notificationTimestamp(Calendar.getInstance().getTime())
                .user(student)
                .build();
        notifications.add(notification);
        List<UserKid> parents = userKidRepository.findByKid_UserId(userId);

        for (UserKid parent : parents) {
            Notification parentNotification = Notification.builder()
                    .isRead(false)
                    .content("You have a new message about your son/daughter : " + message)
                    .notificationTimestamp(Calendar.getInstance().getTime())
                    .user(parent.getUser())
                    .build();
            notifications.add(parentNotification);
        }
        notificationRepository.saveAll(notifications);
    }

    @Override
    public void absenceNotification(User student, Long lessonId) {
        List<UserKid> parents = userKidRepository.findByKid_UserId(student.getUserId());
        Lesson lesson = lessonRepository.getOne(lessonId);
        for (UserKid parent : parents) {
            Notification parentNotification = Notification.builder()
                    .isRead(false)
                    .content("Your son/daughter was absent today at : " + lesson.getSubject().getName())
                    .notificationTimestamp(Calendar.getInstance().getTime())
                    .user(parent.getUser())
                    .build();
            notificationRepository.save(parentNotification);
        }
    }

    @Override
    public List<Notification> getNotifications(Long id) {
        return notificationRepository.findByUser_UserId(id).stream().sorted((Comparator.comparing(Notification::getNotificationTimestamp))).collect(Collectors.toList());
    }

    @Override
    public void changeStatus(Long id) {
        Notification notification = notificationRepository.getOne(id);
        notification.setIsRead(!notification.getIsRead());
        notificationRepository.save(notification);
    }

    @Override
    public boolean checkIfNew(Long id) {
        return notificationRepository.findByUser_UserId(id).stream().filter(Notification::getIsRead).collect(Collectors.toList()).isEmpty();

    }
}
