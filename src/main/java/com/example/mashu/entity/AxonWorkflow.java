package com.example.mashu.entity;

import com.example.mashu.command.CommitWorkflowCommand;
import com.example.mashu.command.CreateWorkflowCommand;
import com.example.mashu.event.WorkflowCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class AxonWorkflow {
  @AggregateIdentifier
  private UUID id;
  private String name;
  private UUID boardId;

  public AxonWorkflow() {}

  @CommandHandler
  public AxonWorkflow(CreateWorkflowCommand cmd) {
    UUID workflowId = UUID.randomUUID();
    String name = cmd.getWorkflowName();
    UUID boardId = cmd.getBoardId();

    AggregateLifecycle.apply(
      new WorkflowCreatedEvent(workflowId, name, boardId)
    );

    AggregateLifecycle.apply(
      new CommitWorkflowCommand(boardId, workflowId, 0)
    );
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public UUID getBoardId() {
    return boardId;
  }

  @EventSourcingHandler
  public void on(WorkflowCreatedEvent event) {
    this.id = event.getId();
    this.name = event.getName();
    this.boardId = event.getBoardId();
  }
}
