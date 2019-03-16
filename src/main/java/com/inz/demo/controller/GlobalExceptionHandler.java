package com.inz.demo.controller;

import com.inz.demo.util.model.response.OperationResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public OperationResponse handleBaseException(DataIntegrityViolationException e) {
        OperationResponse resp = new OperationResponse();
        resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
        resp.setOperationMessage(Objects.requireNonNull(e.getRootCause()).getMessage());
        return resp;
    }


}
