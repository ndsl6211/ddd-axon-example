package com.example.mashu;

import com.example.mashu.event.FakeEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DddApplication {
	public static void main(String[] args) {
		SpringApplication.run(DddApplication.class, args);
	}

}
