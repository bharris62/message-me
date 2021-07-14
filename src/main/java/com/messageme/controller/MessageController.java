package com.messageme.controller;

import com.messageme.domain.Message;
import com.messageme.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {
  private final MessageService messageService;

  @GetMapping("/message/{id}")
  public Message getMessageById(@PathVariable String id){
    return messageService.findById(id);
  }

  @GetMapping("/messages/{recipient}/sender/{sender}")
  public List<Message> getMessagesForRecipient(@PathVariable String recipient, @PathVariable String sender, @RequestParam(required = false) Integer limit){
    return messageService.getMessagesForRecipient(recipient, sender, limit);
  }

  @GetMapping("/messages")
  public List<Message> getAllMessagesWithLimits(@RequestParam(required = false) Integer limit){
    return messageService.getAllRecentMessagesUptoLimitOrDays(limit);
  }

  @PostMapping("/message")
  public Message sendMessage(@RequestBody Message message){

    return messageService.saveMessage(message);
  }
}
