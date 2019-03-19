package com.inz.demo.controller;

import com.inz.demo.domain.Grade;
import com.inz.demo.service.IGradeService;
import com.inz.demo.service.impl.GradeServiceImpl;
import com.inz.demo.util.DTOs.GradeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    private final IGradeService gradeService;

    public GradeController(GradeServiceImpl gradeService) {
        this.gradeService = gradeService;
    }


    @PostMapping(value = "/grade")
    public ResponseEntity createGrade(@RequestBody GradeDTO form) {
        gradeService.addGrade(form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/grade/{id}")
    public ResponseEntity deleteGrade(@PathVariable("id") Long id) {
        if (gradeService.findGradeById(id).isPresent()) {
            gradeService.deleteGrade(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/grade/{id}")
    public ResponseEntity updateGrade(@PathVariable Long id, @RequestBody Double gradeValue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            gradeService.changeGrade(id, gradeValue);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/grade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getGrade(@PathVariable("id") Long id) {
        if (!gradeService.findGradeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(gradeService.findGradeById(id), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/grades")
    public ResponseEntity getGrades() {
        List<Grade> grades = gradeService.getGrades();
        if (grades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grades, HttpStatus.OK);
        }
    }

}
