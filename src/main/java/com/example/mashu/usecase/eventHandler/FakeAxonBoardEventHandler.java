package com.example.mashu.usecase.eventHandler;

import com.example.mashu.event.AxonBoardCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FakeAxonBoardEventHandler {
  public UUID createdBoardId = null;
  public int counter = 0;

  @EventHandler
  public void on(AxonBoardCreatedEvent event) {
    this.createdBoardId = event.getBoardId();
    this.counter++;
  }
}
