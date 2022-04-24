package com.example.mashu;

import com.example.mashu.event.AxonBoardCreatedEvent;
import com.example.mashu.event.FakeEventHandler;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.gateway.DefaultEventGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class DddApplication {

	public static void main(String[] args) {

		EventBus eventBus = SimpleEventBus.builder().build();
		EventGateway gateway = DefaultEventGateway
			.builder()
			.eventBus(eventBus)
			.build();

		gateway.publish(new AxonBoardCreatedEvent(
			UUID.randomUUID(), "123", "123", "123", new Date()
		));

		FakeEventHandler h = new FakeEventHandler();
		System.out.println(h.counter);

		System.out.println("---");

		SpringApplication.run(DddApplication.class, args);
	}

}
