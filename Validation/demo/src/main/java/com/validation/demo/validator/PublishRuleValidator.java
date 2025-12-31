package com.validation.demo.validator;

import com.validation.demo.dto.CreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublishRuleValidator implements ConstraintValidator<PublishRule, CreateRequest> {

    @Override
    public boolean isValid(CreateRequest request, ConstraintValidatorContext context) {
        if (request.status() == null){
            return true;
        }
        if (request.status().equals("PUBLISHED")) {
            if (request.publishAt() == null){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("publishAt").addConstraintViolation();
                return false;
            }
            if (request.publishAt().isBefore(LocalDateTime.now())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("publishAt must be in the future")
                        .addPropertyNode("publishAt").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
