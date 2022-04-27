package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.entity.event.DomainEvent;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericEventMessage;

import java.util.List;
import java.util.UUID;

public class AxonCommiteWorkflowUseCase {
    private final AxonBoardRepository repo;
    private final EventBus eventBus;

    public AxonCommiteWorkflowUseCase(AxonBoardRepository repo, EventBus eventBus) {
        this.repo = repo;
        this.eventBus = eventBus;
    }

    public void execute(AxonCommitWorkflowUseCaseInput input, AxonCommitWorkflowUseCaseOutput output) {
        NewAxonBoard board = repo.getBoardById(UUID.fromString(input.getBoardId())).get();
        board.addWorkflow(input.getWorkflowId(), input.getOrder());

        repo.save(board);

        List<EventMessage<DomainEvent>> events = board
                .getDomainEventList()
                .stream()
                .map(GenericEventMessage::<DomainEvent>asEventMessage)
                .toList();

        eventBus.publish(events);
        board.clearDomainEvents();

        output.setBoardId(board.getId().toString());
    }
}
