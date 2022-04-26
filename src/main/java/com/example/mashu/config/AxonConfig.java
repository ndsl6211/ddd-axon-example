package com.example.mashu.config;

import com.example.mashu.adapter.repository.MysqlAxonBoardRepository;
import com.example.mashu.adapter.repository.MysqlAxonBoardRepositoryPeer;
import com.example.mashu.entity.NewAxonBoard;
import com.example.mashu.usecase.repository.AxonBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

//@Configuration
public class AxonConfig {
//  @Bean
//  public AxonBoardRepository axonBoardRepository(MysqlAxonBoardRepositoryPeer peer) {
//    return new MysqlAxonBoardRepository(peer);
//  }
//
//  @Bean
//  public String preloadBoard(AxonBoardRepository repo) {
//    repo.save(new NewAxonBoard(UUID.randomUUID(), "team1", "board1", "user1"));
//    repo.save(new NewAxonBoard(UUID.randomUUID(), "team1", "board2", "user1"));
//    System.out.println("board preloaded");
//    return "succeed";
//  }
}
