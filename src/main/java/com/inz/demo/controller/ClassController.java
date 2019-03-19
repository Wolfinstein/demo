package com.inz.demo.controller;

import com.inz.demo.domain.User;
import com.inz.demo.service.IClassService;
import com.inz.demo.service.impl.ClassServiceImpl;
import com.inz.demo.util.DTOs.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClassController {

    private final IClassService classService;

    public ClassController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @PostMapping(value = "/classes/add")
    public ResponseEntity createClass() {
        classService.createClass();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/subjects/add/{id}")
    public ResponseEntity createSubject(@RequestBody AddSubjectDTO subject, @PathVariable Long id) {
        classService.addSubject(subject, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/classes/{id}")
    public ResponseEntity deleteClass(@PathVariable("id") Long id) {
        if (classService.findClassById(id).isPresent()) {
            classService.deleteClass(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/subjects/{id}")
    public ResponseEntity deleteSubject(@PathVariable("id") Long id) {
        if (classService.getSubject(id).isPresent()) {
            classService.deleteSubject(id);
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
        List<ClassListDTO> dtos = classService.getClasses();
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/subject/{subjectId}")
    public ResponseEntity updateTeacher(@PathVariable Long subjectId, @Valid @RequestBody String teacherId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            classService.changeTeacher(Long.valueOf(teacherId), subjectId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping(value = "/classes/{id}")
    public ResponseEntity updatePreceptor(@PathVariable Long id, @Valid @RequestBody ClassDTO model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            classService.changeClass(id, model);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @GetMapping(value = "/subjects/{id}")
    public ResponseEntity getClasses(@PathVariable Long id) {
        List<SubjectListDTO> dtos = classService.getSubjectList(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/subjects/getAll")
    public ResponseEntity getSubjects() {
        List<SubjectListDTO> dtos = classService.getSubjects();
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/lessons/{id}")
    public ResponseEntity getLessons(@PathVariable Long id) {
        List<LessonListDTO> dtos = classService.getLessonsList(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity getStudentsBySubjectId(@PathVariable Long id) {
        List<StudentWithGradeDTO> dtos = classService.getStudentListBySubjectId(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/students/data/{id}")
    public ResponseEntity getStudentsData(@PathVariable Long id) {
        List<StudentViewDTO> dtos = classService.getStudentByStudentId(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/students/absences/{id}")
    public ResponseEntity getStudentAbsencesData(@PathVariable Long id) {
        List<StudentAbsencesDTO> dtos = classService.getStudentAbsences(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/students/parent/absences/{id}")
    public ResponseEntity getStudentAbsencesForParent(@PathVariable Long id) {
        List<List<StudentAbsencesDTO>> dtos = classService.getStudentAbsencesForParent(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/students/parent/data/{id}")
    public ResponseEntity getStudentDataForParent(@PathVariable Long id) {
        List<List<StudentViewDTO>> dtos = classService.getStudentsForParent(id);
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/students/teachers")
    public ResponseEntity getTeachers() {
        List<User> teachers = classService.getTeachers();
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/schedule/{id}")
    public ResponseEntity getSchedule(@PathVariable Long id) {
        return new ResponseEntity<>(classService.getClassSchedule(id), HttpStatus.OK);

    }

}
