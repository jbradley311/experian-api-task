package com.example.company.message.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.Message;
import com.example.company.message.service.MessageMappingService;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageMappingServiceTest {

  private static final String EXAMPLE_MESSAGE_DTO_ID = "12345678901";
  private static final String EXAMPLE_COMPANY_NAME = "exampleCompanyNameOne";
  private static final int EXAMPLE_INT = 0;
  private static final double EXAMPLE_DOUBLE = 1.0;
  private static final String EXAMPLE_DTO_DATE = "2020-10-27T14:34:06.132Z";
  private static final BigInteger EXAMPLE_MESSAGE_ID = new BigInteger(
      EXAMPLE_MESSAGE_DTO_ID);
  private static final OffsetDateTime EXAMPLE_ENTITY_DATE = OffsetDateTime.parse(EXAMPLE_DTO_DATE,
      DateTimeFormatter.ISO_DATE_TIME);

  private MessageMappingService messageMappingService;

  @BeforeEach
  void setup() {
    messageMappingService = new MessageMappingService();
  }

  @Test
  void willMapValidDtoToMessageEntity() {
    MessageDto testMessageDto = getMessageDto();
    Message testMessage = getMessage();

    assertEquals(testMessage, messageMappingService.toMessage(testMessageDto));
  }

  private static MessageDto getMessageDto() {
    return MessageDto.builder()
        .messageId(EXAMPLE_MESSAGE_DTO_ID)
        .companyName(EXAMPLE_COMPANY_NAME)
        .registrationDate(EXAMPLE_DTO_DATE)
        .directorsCount(EXAMPLE_INT)
        .score(EXAMPLE_DOUBLE)
        .lastUpdated(EXAMPLE_DTO_DATE)
        .build();
  }

  public static Message getMessage() {
    Message message = new Message();
    message.setMessageId(EXAMPLE_MESSAGE_ID);
    message.setCompanyName(EXAMPLE_COMPANY_NAME);
    message.setRegistrationDate(EXAMPLE_ENTITY_DATE);
    message.setDirectorsCount(EXAMPLE_INT);
    message.setScore(EXAMPLE_DOUBLE);
    message.setLastUpdated(EXAMPLE_ENTITY_DATE);
    return message;
  }
}
