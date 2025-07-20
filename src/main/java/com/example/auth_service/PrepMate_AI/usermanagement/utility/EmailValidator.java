package com.example.auth_service.PrepMate_AI.usermanagement.utility;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class EmailValidator implements ConstraintValidator<ValidEmail, String>
{
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        try{
            return email!=null && pattern.matcher(email).matches();
        }
        catch(Exception e)
        {
            log.error("Error in EmailValidator");
            throw e;
        }
    }
}
