package com.inz.demo.controller;

import com.inz.demo.domain.User;
import com.inz.demo.service.IUserService;
import com.inz.demo.util.DTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final IUserService IUserService;

    @Autowired
    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @PostMapping(value = "/users/add")
    public ResponseEntity create(@RequestBody UserDTO form, HttpServletRequest request) {
        if (IUserService.findUserByLogin(form.getLogin()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            IUserService.createUser(form);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (IUserService.findUserById(id).isPresent()) {
            IUserService.deleteUser(id);
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
            IUserService.updateUser(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/user/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        if (!IUserService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(IUserService.findUserById(id), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        List<User> users = IUserService.getUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}
