package com.example.mashu.usecase.createBoard;

import java.util.UUID;

public class AxonCreateBoardUseCaseOutput {
  private UUID boardId;

  public UUID getBoardId() {
    return this.boardId;
  }

  public void setBoardId(UUID boardId) {
    this.boardId = boardId;
  }
}
