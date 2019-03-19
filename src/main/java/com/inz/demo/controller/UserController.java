package com.inz.demo.controller;

import com.inz.demo.domain.User;
import com.inz.demo.service.IUserService;
import com.inz.demo.service.impl.UserServiceImpl;
import com.inz.demo.util.DTOs.KidDTO;
import com.inz.demo.util.DTOs.UserDTO;
import com.inz.demo.util.DTOs.UserTeacherUpdateDTO;
import com.inz.demo.util.methods.QuotationStringCutter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final IUserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/users/add")
    public ResponseEntity create(@RequestBody UserDTO form) {
        if (userService.findUserByLogin(form.getLogin()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            userService.createUser(form);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (userService.findUserById(id).isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            userService.updateUser(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping(value = "/teacher/{id}")
    public ResponseEntity updateTeacher(@PathVariable Long id, @Valid @RequestBody UserTeacherUpdateDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            userService.updateTeacher(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @GetMapping(value = "/user/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        if (!userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userService.convertUserToDTO(id), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        List<UserDTO> dtos = userService.getUsers();
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/users/potentialKids")
    public ResponseEntity getPotentialKids() {
        List<KidDTO> dtos = userService.getPotentialKids();
        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
    }


    @PutMapping(value = "/users/kids/{id}")
    public ResponseEntity editKids(@PathVariable Long id, @Valid @RequestBody String kidIds) {
        userService.editKids(id, QuotationStringCutter.cutString(kidIds));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/users/edit/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @Valid @RequestBody UserDTO user) {
        userService.editUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
