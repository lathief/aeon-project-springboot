package com.XYZ.Karyawan.entity.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    private Map<String, Object> response = new HashMap<>();
    public Response (Object message, HttpStatus httpStatus) {
        this.response.put("Message", message);
        this.response.put("Status", httpStatus);
    }
}
