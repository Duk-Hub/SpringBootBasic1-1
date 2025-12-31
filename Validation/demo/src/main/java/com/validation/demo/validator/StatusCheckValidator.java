package com.validation.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class StatusCheckValidator implements ConstraintValidator<StatusCheck, String> {

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string.isBlank()) {
            return true;
        }
        if (string.equals("DRAFT")||string.equals("PUBLISHED")||string.equals("ARCHIVED")) {
            return true;
        }
        return false;
    }
}
