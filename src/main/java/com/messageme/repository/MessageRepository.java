package com.messageme.repository;

import com.messageme.domain.Message;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
  List<Message> findAll();

  @Query(
      nativeQuery = true,
      value = "WITH message_children(id, parent_id) AS " +
          "    ( " +
          "      SELECT " +
          "        id, " +
          "        parent_id, " +
          "        sender, " +
          "        recipient,     " +
          "        text, " +
          "        created_at " +
          "      FROM " +
          "        message " +
          "      WHERE " +
          "        id = :id " +
          "      UNION ALL " +
          "      SELECT " +
          "        s2.id, " +
          "        s2.parent_id, " +
          "        s2.sender, " +
          "        s2.recipient, " +
          "        s2.text, " +
          "        s2.created_at " +
          "      FROM " +
          "        message_children s1 " +
          "        INNER JOIN message s2 ON s1.parent_id = s2.id " +
          "    ) " +
          "SELECT " +
          "  * " +
          "FROM " +
          "  message_children; "
  )
  List<Message> findAllChildrenById(Integer id);

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
