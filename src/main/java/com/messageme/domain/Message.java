package com.messageme.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
  static final long serialVersionUID = 42L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "text")
  private String text;

  @Column(name = "sender")
  private String sender;

  @Column(name = "recipient")
  private String recipient;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  public Message(String recipient, String sender, String text) {
    this.recipient = recipient;
    this.sender = sender;
    this.text = text;
  }

  public Message(String recipient, String sender, String text, Date createdAt) {
    this.recipient = recipient;
    this.sender = sender;
    this.text = text;
    this.createdAt = createdAt;
  }
}
