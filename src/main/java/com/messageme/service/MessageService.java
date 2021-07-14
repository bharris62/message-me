package com.messageme.service;

import com.messageme.domain.Message;
import com.messageme.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository messageRepository;

  public Message findById(Integer id) {
    return messageRepository.findById(id).orElse(null);
  }

  public List<Message> findAllChildrenById(Integer id, String order) {
    List<Message> messages = messageRepository.findAllChildrenById(id);

    if (order.equals("0")){
      Collections.reverse(messages);
    }
    return messages;
  }

  public List<Message> getMessagesForRecipient(String recipient, String sender, Integer limit){
    if (limit == null) {
      //TODO: get all messages in last 30 days recip sent to sender
      return messageRepository.findAllByReceiverFromSenderWithDatelimit(recipient, sender);
    }
    //get all messages sender has sent to recipient up to the the max of limit or 100
    Integer validatedLimit = getValidatedLimit(limit);


    return messageRepository.findAllByReceiverFromSenderWithLimit(recipient, sender, limit);
  }

  public List<Message> getAllRecentMessagesUptoLimitOrDays(Integer limit){
    if (limit == null) {
      //TODO: get all messages in last 30 days
      return messageRepository.findMessagesInLast30Days();
    }
    Integer validatedLimit = getValidatedLimit(limit);

    return messageRepository.findMessagesWithLimit(validatedLimit);
  }

  private Integer getValidatedLimit(Integer limit){
    return limit > 100 ? 100 : Math.abs(limit);
  }
}
