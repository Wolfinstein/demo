package com.inz.demo.controller;

import com.inz.demo.domain.Lesson;
import com.inz.demo.service.ILessonService;
import com.inz.demo.service.impl.LessonServiceImpl;
import com.inz.demo.util.DTOs.LessonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LessonController {

    private final ILessonService lessonService;

    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping(value = "/lesson/add")
    public ResponseEntity createLesson(@RequestBody LessonDTO form) {
        lessonService.createLesson(form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/checkPresence/{id}")
    public ResponseEntity checkPresence(@RequestBody Map<Long, String> presences, @PathVariable Long id) {
        lessonService.checkPresence(presences, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/changePresence/{id}")
    public ResponseEntity changePresence(@PathVariable Long id, @RequestBody String presenceType) {
        lessonService.changePresence(id, presenceType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/changeTopic/{id}")
    public ResponseEntity changeTopic(@PathVariable Long id, @RequestBody String topic) {
        lessonService.updateTopic(topic, id);
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

}
