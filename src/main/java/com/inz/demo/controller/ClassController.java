package com.inz.demo.controller;

import com.inz.demo.domain.Class;
import com.inz.demo.service.IClassService;
import com.inz.demo.service.impl.ClassServiceImpl;
import com.inz.demo.util.DTOs.ClassDTO;
import com.inz.demo.util.DTOs.SubjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {

    private final IClassService classService;

    public ClassController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @PostMapping(value = "/class/add")
    public ResponseEntity createClass(@RequestBody ClassDTO form) {
        if (classService.findClassBySign(form.getClassSign()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            classService.createClass(form);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/subject/add/{id}")
    public ResponseEntity createSubject(@RequestBody SubjectDTO form, @PathVariable Long id) {
        classService.addSubject(form, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/student/add/{id}")
    public ResponseEntity addStudents(@RequestBody String studentIds, @PathVariable Long id) {
        classService.addStudents(studentIds, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/class/{id}")
    public ResponseEntity deleteClass(@PathVariable("id") Long id) {
        if (classService.findClassById(id).isPresent()) {
            classService.deleteClass(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/class/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getClass(@PathVariable("id") Long id) {
        if (!classService.findClassById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(classService.findClassById(id), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/classes")
    public ResponseEntity getClasses() {
        List<Class> classes = classService.getClasses();
        if (classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(classes, HttpStatus.OK);
        }
    }

}
