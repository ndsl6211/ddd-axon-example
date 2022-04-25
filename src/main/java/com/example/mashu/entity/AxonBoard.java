//package com.example.mashu.entity;
//
//import com.example.mashu.command.CommitWorkflowCommand;
//import com.example.mashu.command.CreateBoardCommand;
//import com.example.mashu.command.WorkflowCommittedEvent;
//import com.example.mashu.event.BoardCreatedEvent;
//import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.eventsourcing.EventSourcingHandler;
//import org.axonframework.modelling.command.AggregateIdentifier;
//import org.axonframework.modelling.command.AggregateLifecycle;
//import org.axonframework.spring.stereotype.Aggregate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Aggregate
//public class AxonBoard {
//  @AggregateIdentifier
//  private UUID id;
//  private UUID userId;
//  private String name;
//  private UUID teamId;
//  private List<UUID> workflowIdList;
//
//  public AxonBoard() {}
//
//  @CommandHandler
//  public AxonBoard(CreateBoardCommand cmd) {
//    UUID boardId = UUID.randomUUID();
//    UUID userId = cmd.getUserId();
//    String boardName = cmd.getBoardName();
//    UUID teamId = cmd.getTeamId();
//
//    AggregateLifecycle.apply(
//      new BoardCreatedEvent(boardId, userId, boardName, teamId)
//    );
//  }
//
//  @CommandHandler
//  public void handler(CommitWorkflowCommand cmd) {
//    AggregateLifecycle.apply(
//      new WorkflowCommittedEvent(cmd.getBoardId(), cmd.getWorkflowId(), cmd.getOrder())
//    );
//  }
//
//  public UUID getId() {
//    return this.id;
//  }
//
//  public UUID getUserId() {
//    return this.userId;
//  }
//
//  public String getName() {
//    return this.name;
//  }
//
//  public UUID getTeamId() {
//    return this.teamId;
//  }
//
//  public List<UUID> getWorkflowList() {
//    return this.workflowIdList;
//  }
//
//  @EventSourcingHandler
//  public void on(BoardCreatedEvent event) {
//    this.id = event.getBoardId();
//    this.name = event.getBoardName();
//    this.teamId = event.getTeamId();
//    this.userId = event.getUserId();
//    this.workflowIdList = new ArrayList<>();
//  }
//
//  @EventSourcingHandler
//  public void on(WorkflowCommittedEvent event) {
//    this.workflowIdList.add(event.getWorkflowId());
//  }
//}
