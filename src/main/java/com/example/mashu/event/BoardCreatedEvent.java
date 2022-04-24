package com.example.mashu.event;

import java.util.UUID;

public class BoardCreatedEvent {
  private final UUID boardId;
  private final UUID userId;
  private final String boardName;
  private final UUID teamId;

  public BoardCreatedEvent(
    UUID boardId,
    UUID userId,
    String boardName,
    UUID teamId
  ) {
    this.boardId = boardId;
    this.userId = userId;
    this.boardName = boardName;
    this.teamId = teamId;
  }

  public UUID getBoardId() {
    return boardId;
  }

  public String getBoardName() {
    return boardName;
  }

  public UUID getUserId() {
    return userId;
  }

  public UUID getTeamId() {
    return teamId;
  }
}
