package com.example.company.message.util;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.Message;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTestUtils {

  public static final String EXAMPLE_MESSAGE_DTO_ID_ONE = "12345678901";
  public static final String EXAMPLE_MESSAGE_DTO_ID_TWO = "12345678902";
  public static final BigInteger EXAMPLE_MESSAGE_ID_ONE = new BigInteger(
      EXAMPLE_MESSAGE_DTO_ID_ONE);
  public static final BigInteger EXAMPLE_MESSAGE_ID_TWO = new BigInteger(
      EXAMPLE_MESSAGE_DTO_ID_TWO);
  public static final String EXAMPLE_COMPANY_NAME_ONE = "exampleCompanyNameOne";
  public static final String EXAMPLE_COMPANY_NAME_TWO = "exampleCompanyNameTwo";
  public static final String EXAMPLE_DTO_DATE = "2020-10-27T14:34:06.132Z";
  private static final OffsetDateTime EXAMPLE_MESSAGE_DATE = OffsetDateTime.parse(EXAMPLE_DTO_DATE,
      DateTimeFormatter.ISO_DATE_TIME);
  private static final int EXAMPLE_INT = 0;
  private static final double EXAMPLE_DOUBLE = 1.0;

  public static MessageDto getMessageDto(String id, String companyName) {
    return MessageDto.builder()
        .messageId(id)
        .companyName(companyName)
        .registrationDate(EXAMPLE_DTO_DATE)
        .directorsCount(EXAMPLE_INT)
        .score(EXAMPLE_DOUBLE)
        .lastUpdated(EXAMPLE_DTO_DATE)
        .build();
  }

  public static Message getMessage(BigInteger id, String companyName) {
    Message message = new Message();
    message.setMessageId(id);
    message.setCompanyName(companyName);
    message.setRegistrationDate(EXAMPLE_MESSAGE_DATE);
    message.setDirectorsCount(EXAMPLE_INT);
    message.setScore(EXAMPLE_DOUBLE);
    message.setLastUpdated(EXAMPLE_MESSAGE_DATE);
    return message;
  }

}
