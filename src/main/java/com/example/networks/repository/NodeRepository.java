package com.example.networks.repository;

import com.example.networks.domain.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, String> {
    @Query("select n from NodeEntity n where n.type = :type")
    List<NodeEntity> getAllNodesByType(@Param(value = "type") String type);

    @Query("select n from NodeEntity n where n.id = :id and n.type = 'NETWORK'")
    Optional<NodeEntity> getNetworkNodeById(@Param(value = "id") String id);

}
