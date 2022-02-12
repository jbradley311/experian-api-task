package com.example.company.message.unit.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.example.company.message.model.Message;
import com.example.company.message.repository.MessageRepository;
import com.example.company.message.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MessageServiceTest {

  private Message mockMessage;

  @Mock
  private MessageRepository mockMessageRepository;

  @InjectMocks
  private MessageService messageService;

  @BeforeEach
  void setup() {
    mockMessage = mock(Message.class);
    openMocks(this);
  }

  @Test
  void willSaveValidMessageToRepository() {
    messageService.register(mockMessage);
    verify(mockMessageRepository, times(1)).save(mockMessage);
  }

}
