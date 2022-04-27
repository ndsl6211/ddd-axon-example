package com.example.mashu.usecase.eventHandler;

import com.example.mashu.entity.event.AxonBoardCreatedEvent;
import com.example.mashu.entity.event.AxonWorkflowCommitted;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeAxonBoardEventHandler {
  public String createdBoardId = null;
  public int counter = 0;
  public List<String> committedWorkflows = new ArrayList<>();

  @EventHandler
  public void on(AxonBoardCreatedEvent event) {
    this.createdBoardId = event.getBoardId().toString();
    this.counter++;
  }

  @EventHandler
  public void on(AxonWorkflowCommitted event) {
    this.committedWorkflows.add(event.getOrder(), event.getWorkflowId());
  }
}
