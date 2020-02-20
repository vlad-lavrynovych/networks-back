package com.example.networks.services;

import com.example.networks.domain.NodeEntity;
import com.example.networks.enums.NodeTypesEnum;
import com.example.networks.exceptions.InvalidRequestException;
import com.example.networks.exceptions.NoSuchElementException;
import com.example.networks.repository.NodeRepository;
import com.example.networks.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NetworkServiceImpl implements NetworkService {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private Validator validator;

    @Override
    public NodeEntity createNewNetwork(NodeEntity nodeEntity) throws InvalidRequestException {
        if (!validator.isValid(nodeEntity)) {
            throw new InvalidRequestException();
        }
        NodeEntity result = nodeRepository.save(setParentNodesForHierarchy(nodeEntity));
        log.info("Created new network successfully, id :: {}", result.getId());
        return result;
    }

    @Override
    public NodeEntity updateNetwork(String id, NodeEntity nodeDetails) throws InvalidRequestException {
        if (!validator.isValid(nodeDetails)) {
            throw new InvalidRequestException();
        }
        NodeEntity nodeEntity = nodeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Network not found, id " + id));
        nodeEntity.getChildren().clear();
        nodeEntity.getChildren().addAll(nodeDetails.getChildren());
        nodeEntity = setParentNodesForHierarchy(nodeEntity);
        nodeEntity.setDescription(nodeDetails.getDescription());
        nodeEntity.setName(nodeDetails.getName());
        nodeEntity.setParams(nodeDetails.getParams());
        nodeEntity.setType(nodeDetails.getType());
        if (!validator.isValid(nodeEntity)) {
            throw new InvalidRequestException();
        }
        NodeEntity updatedNetwork = nodeRepository.save(nodeEntity);
        log.info("Updated network successfully, id :: {}", id);
        return updatedNetwork;
    }

    @Override
    public Map<String, Boolean> deleteNetworkById(String id) {
        NodeEntity nodeEntity = nodeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Network not found, id  " + id));
        nodeRepository.delete(nodeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Deleted network successfully, id :: {}", id);
        return response;
    }

    @Override
    public List<NodeEntity> getAllNetworks() {
        List<NodeEntity> networks = nodeRepository.getAllNodesByType(NodeTypesEnum.NETWORK.getValue());
        log.info("Returned all networks successfully");
        return networks;
    }

    @Override
    public List<NodeEntity> getChildrenByNetworkId(String id) throws InvalidRequestException {
        List<NodeEntity> result = nodeRepository.getNetworkNodeById(id).orElseThrow(InvalidRequestException::new).getChildren();
        log.info("Returned all children for network, id :: {}", id);
        return result;
    }

    @Override
    public NodeEntity getNetworkById(String id) {
        NodeEntity nodeEntity = nodeRepository.getNetworkNodeById(id).orElseThrow(() -> new NoSuchElementException("Network not found, id " + id));
        log.info("Returned network, id :: {}", id);
        return nodeEntity;
    }

    @Override
    public NodeEntity setParentNodesForHierarchy(NodeEntity nodeEntity) {
        List<NodeEntity> children = nodeEntity.getChildren();
        if (!children.isEmpty()) {
            children.forEach(s -> {
                s.setParent(nodeEntity);
                setParentNodesForHierarchy(s);
            });
        }
        return nodeEntity;
    }
}
