package com.example.mashu.adapter.repository;

import com.example.mashu.adapter.datamapper.AxonWorkflowData;
import com.example.mashu.entity.NewAxonWorkflow;
import com.example.mashu.usecase.repository.AxonWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class MysqlAxonWorkflowRepository implements AxonWorkflowRepository {
  private final MysqlAxonWorkflowRepositoryPeer peer;

  @Autowired
  public MysqlAxonWorkflowRepository(MysqlAxonWorkflowRepositoryPeer peer) {
    this.peer = peer;
  }

  @Override
  public void save(NewAxonWorkflow workflow) {
    this.peer.save(AxonWorkflowData.fromWorkflow(workflow));
  }

  @Override
  public Optional<NewAxonWorkflow> getWorkflowById(UUID id) {
    return this.peer.findById(id).map(AxonWorkflowData::toWorkflow);
  }
}
