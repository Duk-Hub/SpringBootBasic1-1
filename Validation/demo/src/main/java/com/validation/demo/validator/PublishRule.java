package com.validation.demo.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PublishRuleValidator.class, PublishRuleUpdateValidator.class})
public @interface PublishRule {
    String message() default "publishAt is required when status is PUBLISHED";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
