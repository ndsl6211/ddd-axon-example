package com.example.mashu.entity;

import com.example.mashu.entity.event.AxonBoardCreatedEvent;
import com.example.mashu.entity.event.AxonWorkflowCommitted;

import java.util.*;

public class NewAxonBoard extends AggregateRoot {
  private String teamId;
  private String name;
  private String userId;
  private List<String> workflowList;

  public NewAxonBoard(UUID id, String teamId, String name, String userId) {
    super(id);
    this.teamId = teamId;
    this.name = name;
    this.userId = userId;
    this.workflowList = new ArrayList<>();

    this.addDomainEvent(new AxonBoardCreatedEvent(
      this.id,
      this.teamId,
      this.name,
      this.userId,
      new Date()
    ));
  }

  public String getTeamId() {
    return teamId;
  }

  public String getName() {
    return name;
  }

  public String getUserId() {
    return userId;
  }

  public void addWorkflow(String workflowId, int order) {
    if (order > this.workflowList.size())
      order = this.workflowList.size();
    this.workflowList.add(order, workflowId);

    this.addDomainEvent(new AxonWorkflowCommitted(this.id, workflowId, order, new Date()));
  }
}
