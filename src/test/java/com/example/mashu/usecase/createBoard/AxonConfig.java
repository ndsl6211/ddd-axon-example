package com.example.mashu.usecase.createBoard;

import org.axonframework.config.Configurer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AxonConfig {
  @Bean
  public Configurer configureEventHandler(Configurer c) {
    System.out.println("TEST");
    return c;
  }
}
