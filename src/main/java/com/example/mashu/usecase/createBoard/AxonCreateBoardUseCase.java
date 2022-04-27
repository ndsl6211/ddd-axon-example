package com.example.mashu.usecase.createBoard;

import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.DomainEvent;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericEventMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

public class AxonCreateBoardUseCase {
  private final AxonBoardRepository repo;
  private final EventBus eventBus;

  public AxonCreateBoardUseCase(AxonBoardRepository repo, EventBus eventBus) {
    this.repo = repo;
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

    List<EventMessage<DomainEvent>> events = board
      .getDomainEventList()
      .stream()
      .map(GenericEventMessage::<DomainEvent>asEventMessage)
      .toList();

    eventBus.publish(events);
    board.clearDomainEvents();

    output.setBoardId(board.getId());
  }
}
