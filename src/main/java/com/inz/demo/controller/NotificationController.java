package com.inz.demo.controller;

import com.inz.demo.service.INotificationService;
import com.inz.demo.service.impl.NotificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final INotificationService notificationService;

    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/notification/add/{studentId}")
    public ResponseEntity messageNotification(@PathVariable Long studentId, @RequestBody String message) {
            notificationService.writtenNotification(studentId, message);
            return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
