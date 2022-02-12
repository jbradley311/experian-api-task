package com.example.company.message;

import static com.example.company.message.util.MessageTestUtils.EXAMPLE_COMPANY_NAME_ONE;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_COMPANY_NAME_TWO;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_DTO_ID_ONE;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_DTO_ID_TWO;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_ID_ONE;
import static com.example.company.message.util.MessageTestUtils.EXAMPLE_MESSAGE_ID_TWO;
import static com.example.company.message.util.MessageTestUtils.getMessageDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.Message;
import com.example.company.message.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class MessageApplicationTests {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void contextLoads() {
  }

  @Test
  void willCreateNewMessageInRepositoryIfIdDoesNotExist() throws JsonProcessingException {
    //GIVEN
    MessageDto testMessageDtoOne = getMessageDto(EXAMPLE_MESSAGE_DTO_ID_ONE,
        EXAMPLE_COMPANY_NAME_ONE);
    MessageDto testMessageDtoTwo = getMessageDto(EXAMPLE_MESSAGE_DTO_ID_TWO,
        EXAMPLE_COMPANY_NAME_TWO);

    //WHEN
    testRestTemplate.exchange(
        "/message",
        HttpMethod.POST,
        getRequestEntity(testMessageDtoOne),
        String.class);
    testRestTemplate.exchange(
        "/message",
        HttpMethod.POST,
        getRequestEntity(testMessageDtoTwo),
        String.class);

    //THEN
    assertEquals(2, messageRepository.count());
    assertTrue(messageRepository.existsById(EXAMPLE_MESSAGE_ID_ONE));
    assertTrue(messageRepository.existsById(EXAMPLE_MESSAGE_ID_TWO));
  }

  @Test
  void willUpdateMessageInRepositoryIfIdAlreadyExists() throws JsonProcessingException {
    //GIVEN
    MessageDto testMessageDtoOne = getMessageDto(EXAMPLE_MESSAGE_DTO_ID_ONE,
        EXAMPLE_COMPANY_NAME_ONE);
    MessageDto testMessageDtoTwo = getMessageDto(EXAMPLE_MESSAGE_DTO_ID_ONE,
        EXAMPLE_COMPANY_NAME_TWO);

    //WHEN
    testRestTemplate.exchange(
        "/message",
        HttpMethod.POST,
        getRequestEntity(testMessageDtoOne),
        String.class);
    testRestTemplate.exchange(
        "/message",
        HttpMethod.POST,
        getRequestEntity(testMessageDtoTwo),
        String.class);

    //THEN
    String retrievedCompanyName = messageRepository.findById(EXAMPLE_MESSAGE_ID_ONE)
        .map(Message::getCompanyName)
        .orElse(null);

    assertEquals(1, messageRepository.count());
    assertEquals(EXAMPLE_COMPANY_NAME_TWO, retrievedCompanyName);
  }

  private HttpEntity<String> getRequestEntity(MessageDto testMessageDtoOne)
      throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(
        objectMapper.writeValueAsString(testMessageDtoOne),
        headers);
  }

}
