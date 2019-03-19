package com.inz.demo.controller;


import com.inz.demo.repository.LogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLogs() {
        return new ResponseEntity<>(logRepository.findAll(), HttpStatus.OK);

    }

}
