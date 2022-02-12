package com.example.company.message.model;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {

  @Id
  private BigInteger messageId;
  private String companyName;
  private OffsetDateTime registrationDate;
  private double score;
  private int directorsCount;
  private OffsetDateTime lastUpdated;
}
