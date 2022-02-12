package com.example.company.message.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.company.message.controller.MessageController;
import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.SuccessResponse;
import com.example.company.message.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

  private static final String EXAMPLE_MESSAGE_DTO_ID = "12345678901";
  private static final String EXAMPLE_DTO_DATE = "2020-10-27T14:34:06.132Z";

  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MessageService mockMessageService;

  @BeforeEach
  void setup() {
    doNothing().when(mockMessageService).register(any());
  }

  @Test
  void willReturnOkResponseForValidRequestPayload() throws Exception {
    MessageDto messageDto = MessageDto.builder()
        .msgId(EXAMPLE_MESSAGE_DTO_ID)
        .registrationDate(EXAMPLE_DTO_DATE)
        .lastUpdated(EXAMPLE_DTO_DATE)
        .build();
    SuccessResponse successResponse = new SuccessResponse(EXAMPLE_MESSAGE_DTO_ID);

    mockmvc.perform(post("/message")
            .content(objectMapper.writeValueAsString(messageDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(successResponse)));
  }

  @Test
  void willReturnBadRequestResponseForInvalidRequestPayload() throws Exception {
    MessageDto messageDto = MessageDto.builder().msgId("A123F").build();
    mockmvc.perform(post("/message")
            .content(objectMapper.writeValueAsString(messageDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

}
