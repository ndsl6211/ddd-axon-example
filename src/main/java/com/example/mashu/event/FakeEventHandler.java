package com.example.mashu.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FakeEventHandler {
  public UUID createdBoardId = null;
  public int counter = 0;

  public FakeEventHandler() { }

  @EventHandler
  public void on(AxonBoardCreatedEvent event) {
    System.out.println("HI");
    this.createdBoardId = event.getBoardId();
    this.counter++;
  }
}
