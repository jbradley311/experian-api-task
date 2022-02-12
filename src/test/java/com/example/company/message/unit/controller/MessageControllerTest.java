package com.example.company.message.unit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.company.message.controller.MessageController;
import com.example.company.message.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

  @Autowired
  private MockMvc mockmvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void willReturnOkResponseForValidRequestPayload() throws Exception {
    MessageDto messageDto = MessageDto.builder().msgId("messageId").build();
    mockmvc.perform(post("/message")
            .content(objectMapper.writeValueAsString(messageDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void willReturnBadRequestResponseForInvalidRequestPayload() throws Exception {
    MessageDto messageDto = MessageDto.builder().build();
    mockmvc.perform(post("/message")
            .content(objectMapper.writeValueAsString(messageDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

}
