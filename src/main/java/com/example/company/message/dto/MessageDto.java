package com.example.company.message.dto;

import com.example.company.message.validator.DateTimeConstraint;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageDto {

  @NotEmpty
  @Pattern(regexp = "[0-9]+", message = "Id must contain only positive numeric values")
  private String msgId;

  private String companyName;

  @DateTimeConstraint
  private String registrationDate;

  private double score;

  private int directorsCount;

  @DateTimeConstraint
  private String lastUpdated;
}