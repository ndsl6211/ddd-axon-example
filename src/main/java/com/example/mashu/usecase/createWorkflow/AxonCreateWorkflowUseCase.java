package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.entity.NewAxonWorkflow;
import com.example.mashu.entity.event.DomainEvent;
import com.example.mashu.usecase.repository.AxonWorkflowRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericEventMessage;

import java.util.List;
import java.util.UUID;

public class AxonCreateWorkflowUseCase {
    private AxonWorkflowRepository repo;
    private EventBus eventBus;

    public AxonCreateWorkflowUseCase(AxonWorkflowRepository repo, EventBus eventBus) {
        this.repo = repo;
        this.eventBus = eventBus;
    }

    public void execute(AxonCreateWorkflowUseCaseInput input, AxonCreateWorkflowUseCaseOutput output) {
        NewAxonWorkflow workflow = new NewAxonWorkflow(
                UUID.randomUUID(),
                input.getBoardId(),
                input.getWorkflowName()
        );

        repo.save(workflow);

        List<EventMessage<DomainEvent>> events = workflow
                .getDomainEventList()
                .stream()
                .map(GenericEventMessage::<DomainEvent>asEventMessage)
                .toList();

        eventBus.publish(events);
        workflow.clearDomainEvents();

        output.setWorkflowId(workflow.getId());
    }
}
