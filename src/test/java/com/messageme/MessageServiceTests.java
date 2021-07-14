package com.messageme;

import com.messageme.domain.Message;
import com.messageme.service.MessageService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class MessageServiceTests {

  @Autowired
  private MessageService messageService;

  @Test
  public void testGetMessagesForRecipientWithDate() {
    //Data has been checked in repo, want to verify that methods are going to the correct point in the service.

    List<Message> messageList = messageService.getMessagesForRecipient("Blake", "Adam", null);
    assertEquals(messageList.size(), 1);

  }

  @Test
  public void testGetMessagesForRecipientWithLimit() {
    //Data has been checked in repo, want to verify that methods are going to the correct point in the service.

    List<Message> messageList = messageService.getMessagesForRecipient("Adam", "Blake", 2);
    assertEquals(messageList.size(), 2);
  }


  @Test
  public void testGetMessagesForAllWithDate() {
    //Data has been checked in repo, want to verify that methods are going to the correct point in the service.

    List<Message> messageList = messageService.getAllRecentMessagesUptoLimitOrDays(null);
    assertEquals(messageList.size(), 6);
  }

  @Test
  public void testGetMessagesForAllWithLimit() {
    //Data has been checked in repo, want to verify that methods are going to the correct point in the service.

    List<Message> messageList = messageService.getAllRecentMessagesUptoLimitOrDays(2);
    assertEquals(messageList.size(), 2);
  }
}
