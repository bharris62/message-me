package com.messageme.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.messageme.domain.Message;
import com.messageme.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MessageRepository messageRepository;

  public Message saveMessage(Message message) {
    //TODO: Consider adding a queue here, lots of messages could cause problems.
    return messageRepository.saveAndFlush(message);
  }

  public Message findById(String id) {
    return messageRepository.findById(id).orElse(null);
  }

  public List<Message> getMessagesForRecipient(String recipient, String sender, Integer limit) {
    if (limit == null) {
      //TODO: get all messages in last 30 days recip sent to sender
      return messageRepository.findAllByReceiverFromSenderWithDatelimit(recipient, sender);
    }
    //get all messages sender has sent to recipient up to the the max of limit or 100
    Integer validatedLimit = getValidatedLimit(limit);

    return messageRepository.findAllByReceiverFromSenderWithLimit(recipient, sender, validatedLimit);
  }

  public List<Message> getAllRecentMessagesUptoLimitOrDays(Integer limit) {
    if (limit == null) {
      //TODO: get all messages in last 30 days
      return messageRepository.findMessagesInLast30Days();
    }
    Integer validatedLimit = getValidatedLimit(limit);

    return messageRepository.findMessagesWithLimit(validatedLimit);
  }

  private Integer getValidatedLimit(Integer limit) {
    return limit > 100 ? 100 : Math.abs(limit);
  }
}
