package com.example.mashu;

import com.example.mashu.usecase.eventHandler.AxonBoardEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DddApplication {

	public static AxonBoardEventHandler axonBoardEventHandler;

	@Autowired
	public DddApplication(AxonBoardEventHandler axonBoardEventHandler) {
		this.axonBoardEventHandler = axonBoardEventHandler;
	}

	public static void main(String[] args) {

		System.out.println(axonBoardEventHandler.i);

		SpringApplication.run(DddApplication.class, args);
	}
}
