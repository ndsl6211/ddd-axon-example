package com.example.mashu;

import com.example.mashu.usecase.eventHandler.AxonBoardEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DddApplication {

	@Autowired
	public static AxonBoardEventHandler axonBoardEventHandler;


	public static void main(String[] args) {

		System.out.println(axonBoardEventHandler);

		SpringApplication.run(DddApplication.class, args);
	}
}
