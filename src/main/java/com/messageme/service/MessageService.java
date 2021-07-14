package com.messageme.service;

import com.messageme.domain.Message;
import com.messageme.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

  /**
   *
   * Gets the messages for a recipient based on if the client provides a limit or not
   * if limit, use it up to 100, otherwise just get the last 30 days worth.
   *
   * @param recipient
   * @param sender
   * @param limit
   * @return List<Message>
   */
  public List<Message> getMessagesForRecipient(String recipient, String sender, Integer limit) {
    if (limit == null) {
      //TODO: get all messages in last 30 days recip sent to sender
      return messageRepository.findAllByReceiverFromSenderWithDatelimit(recipient, sender);
    }
    //get all messages sender has sent to recipient up to the the max of limit or 100
    Integer validatedLimit = getValidatedLimit(limit);

    return messageRepository.findAllByReceiverFromSenderWithLimit(recipient, sender, validatedLimit);
  }

  /**
   *
   * Gets all the messages  based on if the client provides a limit or not
   * if limit, use it up to 100, otherwise just get the last 30 days worth.
   *
   * @param limit
   * @return List<Message>
   */
  public List<Message> getAllRecentMessagesUptoLimitOrDays(Integer limit) {
    if (limit == null) {
      //TODO: get all messages in last 30 days
      return messageRepository.findMessagesInLast30Days();
    }
    Integer validatedLimit = getValidatedLimit(limit);

    return messageRepository.findMessagesWithLimit(validatedLimit);
  }

  /**
   * Gets a validated limit, up to the max, takes absoluate value in case someone puts -50 and returns 50.
   *
   * @param limit
   * @return Integer
   */
  private Integer getValidatedLimit(Integer limit) {
    return limit > 100 ? 100 : Math.abs(limit);
  }
}
