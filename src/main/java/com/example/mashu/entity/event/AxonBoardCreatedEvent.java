package com.example.mashu.entity.event;

import java.util.Date;
import java.util.UUID;

public class AxonBoardCreatedEvent extends DomainEvent {
  private final UUID boardId;
  private final String teamId;
  private final String boardName;
  private final String userId;

  public AxonBoardCreatedEvent(
    UUID boardId,
    String teamId,
    String boardName,
    String userId,
    Date occurredOn
  ) {
    super(occurredOn);

    this.boardId = boardId;
    this.teamId = teamId;
    this.boardName = boardName;
    this.userId = userId;
  }

  public UUID getBoardId() {
    return boardId;
  }

  public String getBoardName() {
    return boardName;
  }

  public String getUserId() {
    return userId;
  }

  public String getTeamId() {
    return teamId;
  }
}
