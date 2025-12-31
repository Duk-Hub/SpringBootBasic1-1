package com.validation.demo.validator;

import com.validation.demo.dto.UpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublishRuleUpdateValidator implements ConstraintValidator<PublishRule, UpdateRequest> {

    @Override
    public boolean isValid(UpdateRequest request, ConstraintValidatorContext context) {
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
