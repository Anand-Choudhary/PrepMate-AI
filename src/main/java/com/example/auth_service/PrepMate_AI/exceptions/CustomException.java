package com.example.auth_service.PrepMate_AI.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomException extends RuntimeException {
    HttpStatus status;
    String message;
    String errCode;
    List<String> errors = new ArrayList<>();
    String errorDetails;



    public CustomException(HttpStatus status, String message) {
        super (String.format("Status : %s | message : %s", status.toString(), message));
        this.message = message;
        this.status = status;
    }

    public String toString() {
        return String.format("Status : %s  message : %s errors : %s ",status.toString(), message, errors.toString());
    }

    public CustomException(String errCode) {
        super(errCode);
        this.errCode = errCode;
    }
}