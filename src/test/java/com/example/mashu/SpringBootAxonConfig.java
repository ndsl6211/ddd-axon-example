package com.example.mashu;

import com.example.mashu.usecase.eventHandler.FakeAxonBoardEventHandler;
import com.example.mashu.usecase.eventHandler.FakeAxonWorkflowEventHandler;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;


public class SpringBootAxonConfig {
  @Autowired
  public EventBus simpleEventBus;

  @Autowired
  public FakeAxonBoardEventHandler fakeAxonBoardEventHandler;

  @Autowired
  public FakeAxonWorkflowEventHandler fakeAxonWorkflowEventHandler;
}
