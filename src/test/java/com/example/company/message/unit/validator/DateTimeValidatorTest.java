package com.example.company.message.unit.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.company.message.validator.DateTimeValidator;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateTimeValidatorTest {

  private static final String EXAMPLE_DTO_DATE = "2020-10-27T14:34:06.132Z";

  private DateTimeValidator dateTimeValidator;
  private ConstraintValidatorContext mockConstraintValidatorContext;

  @BeforeEach
  void setup() {
    dateTimeValidator = new DateTimeValidator();
    mockConstraintValidatorContext = mock(ConstraintValidatorContext.class);
  }

  @Test
  void isoFormattedUtcDateTimeIsVerifiedAsValid() {
    assertTrue(dateTimeValidator.isValid(EXAMPLE_DTO_DATE, mockConstraintValidatorContext));
  }

  @Test
  void nonIsoFormattedUtcDateTimeIsVerifiedAsInvalid() {
    String invalidIsoFormattedDateTime = "invalidDate";
    assertFalse(
        dateTimeValidator.isValid(invalidIsoFormattedDateTime, mockConstraintValidatorContext));
  }

  @Test
  void nullDateTimeIsVerifiedAsInvalid() {
    assertFalse(dateTimeValidator.isValid(null, mockConstraintValidatorContext));
  }
}

