package com.messageme;

import com.messageme.domain.Message;
import com.messageme.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MessageRepositoryTests {
  @Autowired
  private MessageRepository messageRepository;

  @Test
  public void testCreateMessage(){
    Message message = messageRepository.saveAndFlush(new Message("Carl", "Jim", "HELLO!"));

    assertNotNull(message.getId());
    assertNotNull(message.getCreatedAt());
  }

  @Test
  public void testFindMessageInLast30Days(){
    List<Message> messageList = messageRepository.findMessagesInLast30Days();

    assertEquals(messageList.size(), 6);
  }

  @Test
  public void testFindMessagesWithLimit(){
    Integer LIMIT = 3;

    List<Message> messageList = messageRepository.findMessagesWithLimit(LIMIT);
    assertEquals(messageList.size(), 3);
  }

  @Test
  public void testFindAllByReceiverFromSenderWithLimitAtLimit(){
    List<Message> messageList = messageRepository.findAllByReceiverFromSenderWithLimit("Adam", "Blake", 3);
    assertEquals(messageList.size(), 3);
  }


  @Test
  public void testFindAllByReceiverFromSenderWithLimitUpToLimit(){
    List<Message> messageList = messageRepository.findAllByReceiverFromSenderWithLimit("Blake", "Adam", 3);
    assertEquals(messageList.size(), 2);
  }

  @Test
  public void findAllByReceiverFromSenderWithDatelimit(){
    List<Message> messageList = messageRepository.findAllByReceiverFromSenderWithDatelimit("Blake", "Adam");
    assertEquals(messageList.size(), 1);
  }

}
