package com.example.mashu.usecase.eventHandler;

import com.example.mashu.entity.event.AxonBoardCreatedEvent;
import com.example.mashu.entity.event.AxonWorkflowCommitted;
import com.example.mashu.entity.event.AxonWorkflowCreatedEvent;
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
    System.out.println("axon board created event");
    this.createdBoardId = event.getBoardId().toString();
    this.counter++;
  }

  @EventHandler
  public void on(AxonWorkflowCommitted event) {
    this.committedWorkflows.add(event.getOrder(), event.getWorkflowId());
  }
}
