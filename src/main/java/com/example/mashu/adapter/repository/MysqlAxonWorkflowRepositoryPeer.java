package com.example.mashu.adapter.repository;

import com.example.mashu.adapter.datamapper.AxonWorkflowData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MysqlAxonWorkflowRepositoryPeer extends JpaRepository<AxonWorkflowData, UUID> {
}
