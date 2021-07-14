package com.messageme.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="message")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private int id;

  @Column(name="text")
  private String text;

  @Column(name="sender")
  private String sender;

  @Column(name="recipient")
  private String recipient;

  @Column(name = "created_at")
//  @Temporal(value = TemporalType.DATE)
  private Date createdAt;
}
