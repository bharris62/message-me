package com.messageme.repository;

import com.messageme.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
  List<Message> findAll();

  @Query(
      nativeQuery = true,
      value = "select * from message where created_at  >= current_timestamp() - INTERVAL '30' DAY order by created_at desc"
  )
  List<Message> findMessagesInLast30Days();

  @Query(
      nativeQuery = true,
      value = "select * from message ORDER BY created_at desc LIMIT :limit "
  )
  List<Message> findMessagesWithLimit(Integer limit);

  @Query(
      nativeQuery = true,
      value = "select * from message  where recipient = :receiver and sender = :sender ORDER BY created_at desc limit :limit "
  )
  List<Message> findAllByReceiverFromSenderWithLimit(String receiver, String sender, Integer limit);

  @Query(
      nativeQuery = true,
      value = "select * from message  where recipient = :receiver and sender = :sender and created_at  >= current_timestamp() - INTERVAL '30' DAY order by created_at desc"
  )
  List<Message> findAllByReceiverFromSenderWithDatelimit(String receiver, String sender);
}
