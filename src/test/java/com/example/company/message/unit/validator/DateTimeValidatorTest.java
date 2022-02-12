package com.example.company.message.unit.validator;

import static com.example.company.message.util.MessageTestUtils.EXAMPLE_DTO_DATE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.company.message.validator.DateTimeValidator;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateTimeValidatorTest {

  public static final String INVALID_DATE = "invalidDate";
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
    assertFalse(dateTimeValidator.isValid(INVALID_DATE, mockConstraintValidatorContext));
  }

  @Test
  void nullDateTimeIsVerifiedAsInvalid() {
    assertFalse(dateTimeValidator.isValid(null, mockConstraintValidatorContext));
  }
}

