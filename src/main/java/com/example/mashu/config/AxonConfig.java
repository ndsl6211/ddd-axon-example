package com.example.mashu.config;

import com.example.mashu.event.FakeEventHandler;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

  @Bean
  public FakeEventHandler h() {
    return new FakeEventHandler();
  }

}
