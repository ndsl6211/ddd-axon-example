package com.example.mashu.usecase.createBoard;

import java.util.UUID;

public class CreateBoardUseCaseRes {
  private UUID id;

  public CreateBoardUseCaseRes() { }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
