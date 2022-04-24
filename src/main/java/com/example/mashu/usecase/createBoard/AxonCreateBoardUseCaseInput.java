package com.example.mashu.usecase.createBoard;

import com.example.mashu.entity.NewAxonBoard;

import java.util.UUID;

public class AxonCreateBoardUseCaseInput {
  final private String teamId;
  final private String boardName;
  final private String userId;

  public AxonCreateBoardUseCaseInput(String teamId, String boardName, String userId) {
    this.teamId = teamId;
    this.boardName = boardName;
    this.userId = userId;
  }

  public String getTeamId() {
    return teamId;
  }

  public String getBoardName() {
    return boardName;
  }

  public String getUserId() {
    return userId;
  }
}
