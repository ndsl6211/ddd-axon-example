package com.example.mashu.entity;

import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.AxonWorkflowCreatedEvent;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class NewAxonWorkflowTest {

    @Test
    public void testShouldGenerateWorkflowCreatedEventAfterCreatingWorkflow() {
        UUID workflowId = UUID.randomUUID();
        NewAxonWorkflow workflow = new NewAxonWorkflow(
                workflowId,
                "dummy_board_id",
                "workflow name"
        );

        assertEquals(1, workflow.getDomainEventList().size());
        assertEquals(AxonWorkflowCreatedEvent.class, workflow.getDomainEventList().get(0).getClass());
        assertEquals(workflowId, ((AxonWorkflowCreatedEvent)workflow.getDomainEventList().get(0)).getWorkflowId());
    }
}
