package com.example.mashu.usecase.createBoard;

import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.FakeEventHandler;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.*;
import org.axonframework.eventhandling.gateway.DefaultEventGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AxonCreateBoardUseCaseTest {
  class FakeEventListener2 {
    public UUID createdBoardId;
    public int counter = 0;

    @EventHandler
    public void on(AxonBoardCreatedEvent event) {
      System.out.println("HI");
      this.createdBoardId = event.getBoardId();
      this.counter++;
    }
  }

  @Test
  public void createBoard() {
    AxonBoardRepository repo = new AxonBoardRepository();
    EventBus eventBus = SimpleEventBus.builder().build();
    EventGateway gateway = DefaultEventGateway
      .builder()
      .eventBus(eventBus)
      .build();

//    FakeEventListener listener = new FakeEventListener();
//    eventBus.subscribe(listener);
//    FakeEventListener2 listener2 = new FakeEventListener2();

    FakeEventHandler h = new FakeEventHandler();
    DefaultConfigurer.defaultConfiguration();
    DefaultConfigurer.defaultConfiguration().registerEventHandler(conf -> h);

    String teamId = UUID.randomUUID().toString();
    String userId = UUID.randomUUID().toString();
    AxonCreateBoardUseCaseInput input = new AxonCreateBoardUseCaseInput(
      teamId,
      "board name",
      userId
    );
    AxonCreateBoardUseCaseOutput output = new AxonCreateBoardUseCaseOutput();

    AxonCreateBoardUseCase useCase = new AxonCreateBoardUseCase(repo, gateway);
    useCase.execute(input, output);

    assertNotNull(output.getBoardId());
    assertTrue(repo.getBoardById(output.getBoardId()).isPresent());

//    assertNotNull(listener.createdBoardId);
//    assertEquals(1, listener.counter);

//    assertNotNull(listener2.createdBoardId);
//    assertEquals(1, listener2.counter);

    assertNotNull(h.createdBoardId);
    assertEquals(1, h.counter);
  }
}