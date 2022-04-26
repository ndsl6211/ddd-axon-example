package com.example.mashu.usecase.eventHandler;

import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.AxonWorkflowCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FakeAxonWorkflowEventHandler {
    public UUID createdWorkflowId = null;
    public int counter = 0;

    @EventHandler
    public void on(AxonWorkflowCreatedEvent event) {
        this.createdWorkflowId = event.getWorkflowId();
        this.counter++;
    }
}
