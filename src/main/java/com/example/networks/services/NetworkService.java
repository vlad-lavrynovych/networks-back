package com.example.networks.services;

import com.example.networks.domain.NodeEntity;
import com.example.networks.exceptions.InvalidRequestException;

import java.util.List;
import java.util.Map;

public interface NetworkService {
    NodeEntity createNewNetwork(NodeEntity nodeEntity) throws InvalidRequestException;

    NodeEntity updateNetwork(String id, NodeEntity nodeDetails) throws InvalidRequestException;

    Map<String, Boolean> deleteNetworkById(String id);

    List<NodeEntity> getAllNetworks();

    List<NodeEntity> getChildrenByNetworkId(String id) throws InvalidRequestException;

    NodeEntity getNetworkById(String id);

    NodeEntity setParentNodesForHierarchy(NodeEntity nodeEntity);
}
