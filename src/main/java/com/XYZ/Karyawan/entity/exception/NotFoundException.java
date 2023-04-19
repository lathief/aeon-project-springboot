package com.XYZ.Karyawan.entity.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends BaseException {
    private static final long serialVersionUID = 1L;
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public NotFoundException(String message) {
        super(message, status);
    }
}
