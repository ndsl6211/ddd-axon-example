package com.example.mashu.usecase.createBoard;

import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.BoardCreatedEvent;
import com.example.mashu.event.DomainEvent;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import jdk.jfr.Event;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

public class AxonCreateBoardUseCase {
  private AxonBoardRepository repo;
  private EventBus eventBus;

  public AxonCreateBoardUseCase(AxonBoardRepository repo, EventBus eventBus) {
    this.repo = repo;
//    this.eventBus = eventBus;
    this.eventBus = eventBus;
  }

  public void execute(AxonCreateBoardUseCaseInput input, AxonCreateBoardUseCaseOutput output) {
    NewAxonBoard board = new NewAxonBoard(
      UUID.randomUUID(),
      input.getTeamId(),
      input.getBoardName(),
      input.getUserId()
    );

    repo.save(board);

    List<EventMessage<BoardCreatedEvent>> events = new ArrayList<>();
    for (DomainEvent e: board.getDomainEventList()) {
      events.add(asEventMessage(e));
    }
    System.out.println("[in usecase]" + eventBus.getClass().getSimpleName());
    eventBus.publish(events);

    output.setBoardId(board.getId());
  }
}
