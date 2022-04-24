package com.example.mashu.entity;

import com.example.mashu.command.CommitWorkflowCommand;
import com.example.mashu.command.CreateWorkflowCommand;
import com.example.mashu.event.WorkflowCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AxonWorkflowTest {
  private final FixtureConfiguration<AxonWorkflow> fixture = new AggregateTestFixture<>(AxonWorkflow.class);

  @Test
  public void testCreateWorkflow() {
    UUID boardId = UUID.randomUUID();
    String workflowName = "Sprint Planning";

    UUID[] resultWorkflowId = new UUID[1];
    fixture
      .givenNoPriorActivity()
      .when(new CreateWorkflowCommand(boardId, workflowName))
      .expectSuccessfulHandlerExecution()
      .expectState(workflow -> {
        resultWorkflowId[0] = workflow.getId();
        assertNotNull(workflow.getId());
        assertEquals(boardId, workflow.getBoardId());
        assertEquals(workflowName, workflow.getName());
      })
      .expectEvents(
        new WorkflowCreatedEvent(resultWorkflowId[0], workflowName, boardId),
        new CommitWorkflowCommand(boardId, resultWorkflowId[0], 0)
      );
  }
}