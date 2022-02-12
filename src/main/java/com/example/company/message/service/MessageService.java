package com.example.company.message.service;

import com.example.company.message.model.Message;
import com.example.company.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  public void register(Message message) {
    messageRepository.save(message);
  }
}
