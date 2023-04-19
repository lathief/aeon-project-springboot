package com.XYZ.Karyawan.advice;

import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class KaryawanExceptionController {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response> exception(NotFoundException exception) {
        Map result = new HashMap<>();
        return new ResponseEntity<>(new Response(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
