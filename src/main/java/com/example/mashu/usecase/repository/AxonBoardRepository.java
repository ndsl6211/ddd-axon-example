package com.example.mashu.usecase.repository;

import com.example.mashu.entity.NewAxonBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AxonBoardRepository {
  private Map<UUID, NewAxonBoard> boardMap;

  public AxonBoardRepository() {
    this.boardMap = new HashMap<>();
  }

  public void save(NewAxonBoard board) {
    this.boardMap.put(board.getId(), board);
  }

  public Optional<NewAxonBoard> getBoardById(UUID id) {
    return Optional.ofNullable(this.boardMap.get(id));
  }
}
