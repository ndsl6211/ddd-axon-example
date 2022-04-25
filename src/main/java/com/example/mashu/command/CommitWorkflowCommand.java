//package com.example.mashu.command;
//
//import org.axonframework.modelling.command.TargetAggregateIdentifier;
//
//import java.util.UUID;
//
//public class CommitWorkflowCommand {
//  @TargetAggregateIdentifier
//  private final UUID boardId;
//  private final UUID workflowId;
//  private final int order;
//
//  public CommitWorkflowCommand(UUID boardId, UUID workflowId, int order) {
//    this.boardId = boardId;
//    this.workflowId = workflowId;
//    this.order = order;
//  }
//
//  public UUID getBoardId() {
//    return boardId;
//  }
//
//  public UUID getWorkflowId() {
//    return workflowId;
//  }
//
//  public int getOrder() {
//    return order;
//  }
//}
