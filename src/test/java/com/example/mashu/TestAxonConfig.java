package com.example.mashu;

import com.example.mashu.usecase.eventHandler.FakeAxonBoardEventHandler;
import com.example.mashu.usecase.eventHandler.FakeAxonWorkflowEventHandler;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestAxonConfig {

  @Autowired
  public EventBus simpleEventBus;

  @Autowired
  public FakeAxonBoardEventHandler fakeAxonBoardEventHandler;

  @Autowired
  public FakeAxonWorkflowEventHandler fakeAxonWorkflowEventHandler;

//  @Bean
//  public FakeEventHandler fakeEventHandler() {
//    return new FakeEventHandler(10);
//  }

//  @Bean
//  public EventBus simpleEventBus() {
//    return this.simpleEventBus;
//  }
}
