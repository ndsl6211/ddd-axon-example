package com.example.mashu.usecase.createWorkflow;

import com.example.mashu.entity.NewAxonWorkflow;
import com.example.mashu.event.AxonWorkflowCreatedEvent;
import com.example.mashu.event.DomainEvent;
import com.example.mashu.adapter.repository.InMeomoryAxonWorkflowRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

public class AxonCreateWorkflowUseCase {
    private InMeomoryAxonWorkflowRepository repo;
    private EventBus eventBus;

    public AxonCreateWorkflowUseCase(InMeomoryAxonWorkflowRepository repo, EventBus eventBus) {
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

        List<EventMessage<AxonWorkflowCreatedEvent>> events = new ArrayList<>();
        for (DomainEvent e: workflow.getDomainEventList()) {
            events.add(asEventMessage(e));
        }

        eventBus.publish(events);

        output.setWorkflowId(workflow.getId());
    }
}
