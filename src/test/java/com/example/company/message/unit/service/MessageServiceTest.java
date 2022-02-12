package com.example.company.message.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.repository.MessageRepository;
import com.example.company.message.service.MessageMappingService;
import com.example.company.message.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MessageServiceTest {

  private MessageDto mockMessageDto;

  @Mock
  private MessageRepository mockMessageRepository;

  @Mock
  private MessageMappingService messageMappingService;

  @InjectMocks
  private MessageService messageService;

  @BeforeEach
  void setup() {
    mockMessageDto = mock(MessageDto.class);
    openMocks(this);
  }

  @Test
  void willSaveValidMessageToRepository() {
    messageService.register(mockMessageDto);

    verify(messageMappingService, times(1)).toMessage(any());
    verify(mockMessageRepository, times(1)).save(any());
  }

}
