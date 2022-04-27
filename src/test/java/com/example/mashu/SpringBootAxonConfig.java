package com.example.mashu;

import com.example.mashu.adapter.repository.InMemoryAxonBoardRepository;
import com.example.mashu.usecase.eventHandler.FakeAxonBoardEventHandler;
import com.example.mashu.usecase.eventHandler.FakeAxonWorkflowEventHandler;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringBootAxonConfig {
  @Autowired
  public EventBus simpleEventBus;

  @Autowired
  public FakeAxonBoardEventHandler fakeAxonBoardEventHandler;

  @Autowired
  public FakeAxonWorkflowEventHandler fakeAxonWorkflowEventHandler;

  public AxonBoardRepository boardRepo = new InMemoryAxonBoardRepository();
}
