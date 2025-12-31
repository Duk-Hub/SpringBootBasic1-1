package com.validation.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusCheckValidator.class)
public @interface StatusCheck {

    String message() default "status must be one of [DRAFT, PUBLISHED, ARCHIVED]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
