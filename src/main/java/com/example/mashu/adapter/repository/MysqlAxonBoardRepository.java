package com.example.mashu.adapter.repository;

import com.example.mashu.adapter.datamapper.AxonBoardData;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class MysqlAxonBoardRepository implements AxonBoardRepository {

  private final MysqlAxonBoardRepositoryPeer peer;

  @Autowired
  public MysqlAxonBoardRepository(MysqlAxonBoardRepositoryPeer peer) {
    this.peer = peer;
  }

  public void save(NewAxonBoard board) {
    this.peer.save(AxonBoardData.fromBoard(board));
  }

  public Optional<NewAxonBoard> getBoardById(UUID id) {
    return this.peer.findById(id).map(AxonBoardData::toBoard);
  }
}
