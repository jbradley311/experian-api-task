package com.example.company.message.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeConstraint {

  String INVALID_DATE_FORMAT_MESSAGE = "Date must be formatted as ISO-8601 UTC date time";

  String message() default INVALID_DATE_FORMAT_MESSAGE;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
