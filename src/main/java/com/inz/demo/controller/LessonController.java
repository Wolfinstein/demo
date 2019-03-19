package com.inz.demo.controller;

import com.inz.demo.domain.Lesson;
import com.inz.demo.service.ILessonService;
import com.inz.demo.service.impl.LessonServiceImpl;
import com.inz.demo.util.DTOs.PresenceListDTO;
import com.inz.demo.util.methods.QuotationStringCutter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonController {

    private final ILessonService lessonService;

    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping(value = "/lesson/add/{id}")
    public ResponseEntity createLesson(@PathVariable Long id) {
        lessonService.createLesson(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/presences/edit/{id}")
    public ResponseEntity changePresence(@PathVariable Long id, @RequestBody String presenceType) {
        lessonService.changePresence(id, QuotationStringCutter.cutString(presenceType));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/lesson/changeTopic/{id}")
    public ResponseEntity changeTopic(@PathVariable Long id, @RequestBody String topic) {
        lessonService.updateTopic(QuotationStringCutter.cutString(topic), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/lesson/{id}")
    public ResponseEntity deleteLesson(@PathVariable("id") Long id) {
        if (lessonService.getLesson(id).isPresent()) {
            lessonService.deleteLesson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/lesson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLesson(@PathVariable("id") Long id) {
        if (!lessonService.getLesson(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessonService.getLesson(id), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/lessons")
    public ResponseEntity getLessons() {
        List<Lesson> lessons = lessonService.getLessons();
        if (lessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lessons, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/presences/{id}")
    public ResponseEntity getPresences(@PathVariable Long id) {
        List<PresenceListDTO> dtos = lessonService.getPresencesList(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }


}
