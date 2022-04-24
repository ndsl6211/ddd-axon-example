package com.example.mashu.usecase.createBoard;

import java.util.UUID;

public class CreateBoardUseCaseReq {
  private UUID userId;
  private String boardName;
  private UUID teamId;

  public CreateBoardUseCaseReq(UUID userId, String boardName, UUID teamId) {
    this.userId = userId;
    this.boardName = boardName;
    this.teamId = teamId;
  }

  public void setBoardName(String name) {
    this.boardName = name;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public void setTeamId(UUID teamId) {
    this.teamId = teamId;
  }

  public String getBoardName() {
    return this.boardName;
  }

  public UUID getUserId() {
    return this.userId;
  }

  public UUID getTeamId() {
    return this.teamId;
  }
}
