package com.pss.pss.article.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CategoryCheckValidator implements ConstraintValidator<CategoryCheck, String> {

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string == null) {
            return true;
        }

        return (string.equals("TECH")||string.equals("LIFE")||string.equals("NEWS"));
    }
}
