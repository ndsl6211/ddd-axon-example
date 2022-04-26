package com.example.mashu;

import com.example.mashu.adapter.repository.MysqlAxonBoardRepository;
import com.example.mashu.adapter.repository.MysqlAxonBoardRepositoryPeer;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class DddApplication {
	public static void main(String[] args) {
		SpringApplication.run(DddApplication.class, args);
	}
}
