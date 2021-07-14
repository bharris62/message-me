package com.messageme.controller;

import com.messageme.domain.Message;
import com.messageme.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {
  private final MessageService messageService;

  @GetMapping("/message/{id}")
  public Message getMessageById(@PathVariable Integer id){
    return messageService.findById(id);
  }

  @GetMapping("/message/{id}/children")
  public List<Message> getMessageChildrenById(@PathVariable Integer id, @RequestParam(defaultValue = "0") String order){
    //   ordering
    //0 oldest to newest
    //1 newest to oldest
    return messageService.findAllChildrenById(id, order);
  }

  @GetMapping("/messages/{recipient}/sender/{sender}")
  public List<Message> getMessagesForRecipient(@PathVariable String recipient, @PathVariable String sender, @RequestParam(required = false) Integer limit){
    return messageService.getMessagesForRecipient(recipient, sender, limit);
  }

  @GetMapping("/messages")
  public List<Message> getAllMessagesWithLimits(@RequestParam(required = false) Integer limit){
    return messageService.getAllRecentMessagesUptoLimitOrDays(limit);
  }
}
