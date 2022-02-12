package com.example.company.message.controller;

import com.example.company.message.dto.MessageDto;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @PostMapping(path = "/message", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> postMessage(@Valid @RequestBody MessageDto messageDto) {
    return new ResponseEntity<>("okay", HttpStatus.OK);
  }
}
