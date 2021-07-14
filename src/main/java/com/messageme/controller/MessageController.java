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

  /**
   *
   * @param id
   * @return Message
   */
  @GetMapping("/message/{id}")
  public Message getMessageById(@PathVariable String id){
    //Get 1 message by the message id
    return messageService.findById(id);
  }

  /**
   *
   * @param recipient
   * @param sender
   * @param limit
   * @return List<Message>
   */
  @GetMapping("/messages/{recipient}/sender/{sender}")
  public List<Message> getMessagesForRecipient(@PathVariable String recipient, @PathVariable String sender, @RequestParam(required = false) Integer limit){
    //get messages to recipient from sender, by either a limit (up to 100) or
    return messageService.getMessagesForRecipient(recipient, sender, limit);
  }

  /**
   *
   * @param limit
   * @return List<Message>
   */
  @GetMapping("/messages")
  public List<Message> getAllMessagesWithLimits(@RequestParam(required = false) Integer limit){
    return messageService.getAllRecentMessagesUptoLimitOrDays(limit);
  }

  /**
   *
   * @param message
   * @return Message
   */
  @PostMapping("/message")
  public Message sendMessage(@RequestBody Message message){

    return messageService.saveMessage(message);
  }
}
