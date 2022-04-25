package com.example.mashu;

import com.example.mashu.event.FakeEventHandler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestAxonConfig {

  @Autowired
  public EventBus simpleEventBus;

  @Autowired
  public FakeEventHandler fakeEventHandler;

//  @Bean
//  public FakeEventHandler fakeEventHandler() {
//    return new FakeEventHandler(10);
//  }

//  @Bean
//  public EventBus simpleEventBus() {
//    return this.simpleEventBus;
//  }
}
