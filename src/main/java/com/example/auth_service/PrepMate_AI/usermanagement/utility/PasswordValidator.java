package com.example.auth_service.PrepMate_AI.usermanagement.utility;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        try{
            if(password == null || password.length() <8)
            {
                return false;
            }
            boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
            boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
            boolean hasDigit = password.chars().anyMatch(Character::isDigit);
            boolean hasSpecialChar = password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]|,./?><".contains(Character.toString(ch)));

            return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
        }
        catch(Exception e)
        {
            log.error("Error in PasswordValidator");
            throw e;
        }
    }
}
