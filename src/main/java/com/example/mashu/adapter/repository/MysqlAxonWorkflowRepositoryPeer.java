package com.example.mashu.adapter.repository;

import com.example.mashu.adapter.datamapper.AxonWorkflowData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MysqlAxonWorkflowRepositoryPeer extends JpaRepository<AxonWorkflowData, UUID> {
}
