package com.example.mashu.usecase.repository;

import com.example.mashu.entity.NewAxonBoard;

import java.util.Optional;
import java.util.UUID;

public interface AxonBoardRepository {
  void save(NewAxonBoard board);
  Optional<NewAxonBoard> getBoardById(UUID id);
}
