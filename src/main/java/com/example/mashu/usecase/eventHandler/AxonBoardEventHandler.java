package com.example.mashu.usecase.eventHandler;

import com.example.mashu.entity.event.AxonWorkflowCreatedEvent;
import com.example.mashu.usecase.createWorkflow.AxonCommitWorkflowUseCaseInput;
import com.example.mashu.usecase.createWorkflow.AxonCommitWorkflowUseCaseOutput;
import com.example.mashu.usecase.createWorkflow.AxonCommiteWorkflowUseCase;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AxonBoardEventHandler {
  @Autowired
  private EventBus simpleEventBus;

  @Autowired
  private AxonBoardRepository boardRepo;

  public int i = 10;

//  @Autowired
//  public AxonBoardEventHandler(EventBus simpleEventBus, AxonBoardRepository boardRepo) {
//    this.simpleEventBus = simpleEventBus;
//    this.boardRepo = boardRepo;
//  }
  public AxonBoardEventHandler() {
    System.out.println("constructor");
  }

  @EventHandler
  public void on(AxonWorkflowCreatedEvent event) {
    AxonCommitWorkflowUseCaseInput input = new AxonCommitWorkflowUseCaseInput();
    input.setBoardId(event.getBoardId());
    input.setWorkflowId(event.getWorkflowId().toString());
    input.setOrder(0);
    AxonCommitWorkflowUseCaseOutput output = new AxonCommitWorkflowUseCaseOutput();

    AxonCommiteWorkflowUseCase useCase = new AxonCommiteWorkflowUseCase(boardRepo, simpleEventBus);
    useCase.execute(input, output);
  }
}
