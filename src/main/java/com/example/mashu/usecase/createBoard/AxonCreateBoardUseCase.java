package com.example.mashu.usecase.createBoard;

import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.DomainEvent;
import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

public class AxonCreateBoardUseCase {
  private InMemoryAxonBoardRepository repo;
  private EventBus eventBus;

  public AxonCreateBoardUseCase(InMemoryAxonBoardRepository repo, EventBus eventBus) {
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

    List<EventMessage<AxonBoardCreatedEvent>> events = new ArrayList<>();
    for (DomainEvent e: board.getDomainEventList()) {
      events.add(asEventMessage(e));
    }

    eventBus.publish(events);

    output.setBoardId(board.getId());
  }
}
