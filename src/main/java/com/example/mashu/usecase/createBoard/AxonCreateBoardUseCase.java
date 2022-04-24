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
//  private EventBus eventBus;
  private EventGateway gateway;

  public AxonCreateBoardUseCase(AxonBoardRepository repo, EventGateway gateway) {
    this.repo = repo;
//    this.eventBus = eventBus;
    this.gateway = gateway;
  }

  public void execute(AxonCreateBoardUseCaseInput input, AxonCreateBoardUseCaseOutput output) {
    NewAxonBoard board = new NewAxonBoard(
      UUID.randomUUID(),
      input.getTeamId(),
      input.getBoardName(),
      input.getUserId()
    );

    repo.save(board);

//    List<EventMessage<Object>> eventMessageList = board.getDomainEventList()
//      .stream()
//      .map(GenericEventMessage::asEventMessage)
//      .toList();
//
//    eventBus.publish(eventMessageList);

    List<EventMessage<BoardCreatedEvent>> events = new ArrayList<>();
    for (DomainEvent e: board.getDomainEventList()) {
      events.add(asEventMessage(e));
    }
//    eventBus.publish(events);
    this.gateway.publish(new AxonBoardCreatedEvent(UUID.randomUUID(), "123", "123", "123", new Date()));

    output.setBoardId(board.getId());
  }
}
