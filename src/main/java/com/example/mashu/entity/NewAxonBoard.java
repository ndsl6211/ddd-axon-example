package com.example.mashu.entity;

import com.example.mashu.event.AxonBoardCreatedEvent;
import java.util.Date;
import java.util.UUID;

public class NewAxonBoard extends AggregateRoot {
  private String teamId;
  private String name;
  private String userId;

  public NewAxonBoard(UUID id, String teamId, String name, String userId) {
    super(id);
    this.teamId = teamId;
    this.name = name;
    this.userId = userId;

    this.addDomainEvent(new AxonBoardCreatedEvent(
      this.id,
      this.teamId,
      this.name,
      this.userId,
      new Date()
    ));
  }

  public String getTeamId() {
    return teamId;
  }

  public String getName() {
    return name;
  }

  public String getUserId() {
    return userId;
  }
}
