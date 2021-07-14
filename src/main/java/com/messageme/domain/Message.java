package com.messageme.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="message")
public class Message {
  static final long serialVersionUID = 42L;

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;

  @Column(name="text")
  private String text;

  @Column(name="sender")
  private String sender;

  @Column(name="recipient")
  private String recipient;

  @Column(name = "created_at")
  @CreationTimestamp
//  @Temporal(value = TemporalType.DATE)
  private Date createdAt;
}
