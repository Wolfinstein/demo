package com.inz.demo.controller;

import com.inz.demo.service.INotificationService;
import com.inz.demo.service.impl.NotificationServiceImpl;
import com.inz.demo.util.methods.QuotationStringCutter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    private final INotificationService notificationService;

    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/notification/add/{id}")
    public ResponseEntity messageNotification(@PathVariable Long id, @RequestBody String message) {
        notificationService.writtenNotification(id, QuotationStringCutter.cutString(message));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping(value = "/notifications/{id}")
    public ResponseEntity getNotifications(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.getNotifications(id), HttpStatus.OK);

    }

    @GetMapping(value = "/notifications/changeStatus/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        notificationService.changeStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/notifications/color/{id}")
    public ResponseEntity checkIfNew(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.checkIfNew(id), HttpStatus.OK);
    }

}
