package com.example.company.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.Message;
import com.example.company.message.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
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

  private static final String EXAMPLE_MESSAGE_DTO_ID_ONE = "12345678901";
  private static final String EXAMPLE_MESSAGE_DTO_ID_TWO = "12345678902";
  private static final BigInteger EXAMPLE_MESSAGE_ID_ONE = new BigInteger(
      EXAMPLE_MESSAGE_DTO_ID_ONE);
  private static final BigInteger EXAMPLE_MESSAGE_ID_TWO = new BigInteger(
      EXAMPLE_MESSAGE_DTO_ID_TWO);
  private static final String EXAMPLE_COMPANY_NAME_ONE = "exampleCompanyNameOne";
  private static final String EXAMPLE_COMPANY_NAME_TWO = "exampleCompanyNameTwo";
  private static final int EXAMPLE_INT = 0;
  private static final double EXAMPLE_DOUBLE = 1.0;
  private static final String EXAMPLE_DTO_DATE = "2020-10-27T14:34:06.132Z";

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

  @Test
  void willNotInteractWithRepositoryIfInvalidMessageRequestPayload()
      throws JsonProcessingException {
    //GIVEN
    MessageDto testInvalidMessageDto = MessageDto.builder().build();

    //WHEN
    testRestTemplate.exchange(
        "/message",
        HttpMethod.POST,
        getRequestEntity(testInvalidMessageDto),
        String.class);

    //THEN
    assertEquals(0, messageRepository.count());
  }

  private HttpEntity<String> getRequestEntity(MessageDto testMessageDtoOne)
      throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(
        objectMapper.writeValueAsString(testMessageDtoOne),
        headers);
  }

  private static MessageDto getMessageDto(String id, String companyName) {
    return MessageDto.builder()
        .messageId(id)
        .companyName(companyName)
        .registrationDate(EXAMPLE_DTO_DATE)
        .directorsCount(EXAMPLE_INT)
        .score(EXAMPLE_DOUBLE)
        .lastUpdated(EXAMPLE_DTO_DATE)
        .build();
  }

}
