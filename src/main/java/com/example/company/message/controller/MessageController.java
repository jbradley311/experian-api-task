package com.example.company.message.controller;

import com.example.company.message.dto.MessageDto;
import com.example.company.message.service.MessageService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @PostMapping(path = "/message", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> postMessage(@Valid @RequestBody MessageDto messageDto) {
    messageService.register(messageDto);
    return new ResponseEntity<>("okay", HttpStatus.OK);
  }
}
