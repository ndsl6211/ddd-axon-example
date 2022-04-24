package com.example.mashu.event;

import java.util.UUID;

public class WorkflowCreatedEvent {
  private UUID id;
  private String name;
  private UUID boardId;

  public WorkflowCreatedEvent(
    UUID id,
    String name,
    UUID boardId
  ) {
    this.id = id;
    this.name = name;
    this.boardId = boardId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getBoardId() {
    return boardId;
  }

  public void setBoardId(UUID boardId) {
    this.boardId = boardId;
  }
}
