package com.example.company.message.service;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.model.Message;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class MessageServiceMapper {

  public Message toMessage(MessageDto messageDto) {
    Message message = new Message();
    message.setMessageId(toBigInteger(messageDto.getMsgId()));
    message.setCompanyName(messageDto.getCompanyName());
    message.setDirectorsCount(messageDto.getDirectorsCount());
    message.setLastUpdated(toOffsetDateTime(messageDto.getLastUpdated()));
    message.setRegistrationDate(toOffsetDateTime(messageDto.getRegistrationDate()));
    message.setScore(messageDto.getScore());
    return message;
  }

  private static BigInteger toBigInteger(String id) {
    return new BigInteger(id);
  }

  private static OffsetDateTime toOffsetDateTime(String dateTime) {
    return OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
  }
}
