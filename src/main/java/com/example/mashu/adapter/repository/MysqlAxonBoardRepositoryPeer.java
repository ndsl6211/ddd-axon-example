package com.example.mashu.adapter.repository;

import com.example.mashu.adapter.datamapper.AxonBoardData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MysqlAxonBoardRepositoryPeer extends JpaRepository<AxonBoardData, UUID> {

}
