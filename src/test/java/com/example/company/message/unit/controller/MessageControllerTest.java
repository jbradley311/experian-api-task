package com.example.company.message.unit.controller;

import static com.example.company.message.util.MessageTestUtils.EXAMPLE_DTO_DATE;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_DTO_ID_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.company.message.controller.MessageController;
import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.ErrorResponse;
import com.example.company.message.model.SuccessResponse;
import com.example.company.message.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

  private static final String EXAMPLE_INVALID_MESSAGE_DTO_ID = "A123F";

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
        .messageId(EXAMPLE_MESSAGE_DTO_ID_ONE)
        .registrationDate(EXAMPLE_DTO_DATE)
        .lastUpdated(EXAMPLE_DTO_DATE)
        .build();
    SuccessResponse successResponse = new SuccessResponse(EXAMPLE_MESSAGE_DTO_ID_ONE);

    mockmvc.perform(post("/message")
            .content(objectMapper.writeValueAsString(messageDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(successResponse)));
  }

  @Test
  void willReturnBadRequestResponseForInvalidRequestPayload() throws Exception {
    MessageDto messageDto = MessageDto.builder().messageId(EXAMPLE_INVALID_MESSAGE_DTO_ID).build();

    MvcResult result = mockmvc.perform(post("/message")
            .content(objectMapper.writeValueAsString(messageDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    assertEquals(3, errorResponse.getErrors().size());
  }

}
