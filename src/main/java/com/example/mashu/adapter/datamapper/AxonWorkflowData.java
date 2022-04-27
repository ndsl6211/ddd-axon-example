package com.example.mashu.adapter.datamapper;

import com.example.mashu.entity.NewAxonWorkflow;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "workflow")
public class AxonWorkflowData {
  @Id
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "board_id")
  private String boardId;

  @Column(name = "workflow_name")
  private String workflowName;

  public static AxonWorkflowData fromWorkflow(NewAxonWorkflow workflow) {
    AxonWorkflowData workflowData = new AxonWorkflowData();

    workflowData.setId(workflow.getId());
    workflowData.setWorkflowName(workflow.getWorkflowName());
    workflowData.setBoardId(workflow.getBoardId());

    return workflowData;
  }

  public static NewAxonWorkflow toWorkflow(AxonWorkflowData workflowData) {
    return new NewAxonWorkflow(
      workflowData.getId().toString(),
      workflowData.getBoardId(),
      workflowData.getWorkflowName()
    );
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setBoardId(String boardId) {
    this.boardId = boardId;
  }

  public void setWorkflowName(String workflowName) {
    this.workflowName = workflowName;
  }

  public UUID getId() {
    return id;
  }

  public String getBoardId() {
    return boardId;
  }

  public String getWorkflowName() {
    return workflowName;
  }
}
