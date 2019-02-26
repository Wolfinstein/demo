package com.inz.demo.controller;

import com.inz.demo.model.Users;
import com.inz.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsersController {


    private final  UsersRepository usersRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

}
