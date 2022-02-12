package com.example.company.message.service;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private MessageMappingService messageMappingService;

  public void register(MessageDto messageDto) {
    messageRepository.save(messageMappingService.toMessage(messageDto));
  }
}
