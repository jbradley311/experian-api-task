package com.example.company.message.unit.service;

import static com.example.company.message.util.MessageTestUtils.EXAMPLE_COMPANY_NAME_ONE;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_DTO_ID_ONE;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_ID_ONE;
import static com.example.company.message.util.MessageTestUtils.getMessage;
import static com.example.company.message.util.MessageTestUtils.getMessageDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.Message;
import com.example.company.message.service.MessageMappingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageMappingServiceTest {

  private MessageMappingService messageMappingService;

  @BeforeEach
  void setup() {
    messageMappingService = new MessageMappingService();
  }

  @Test
  void willMapValidDtoToMessageEntity() {
    MessageDto testMessageDto = getMessageDto(EXAMPLE_MESSAGE_DTO_ID_ONE, EXAMPLE_COMPANY_NAME_ONE);
    Message testMessage = getMessage(EXAMPLE_MESSAGE_ID_ONE, EXAMPLE_COMPANY_NAME_ONE);

    assertEquals(testMessage, messageMappingService.toMessage(testMessageDto));
  }

}
