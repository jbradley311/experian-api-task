package com.example.company.message.validator;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<DateTimeConstraint, String> {

  @Override
  public void initialize(DateTimeConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String dateTime, ConstraintValidatorContext constraintValidatorContext) {
    try {
      OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
    } catch (DateTimeParseException dateTimeParseException) {
      return false;
    }
    return true;
  }
}