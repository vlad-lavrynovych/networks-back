package com.example.networks.services;

import com.example.networks.domain.NodeEntity;
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
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private Validator validator;

    @Override
    public NodeEntity createNewNode(String parentId, NodeEntity nodeEntity) throws InvalidRequestException {
        NodeEntity parent = nodeRepository.findById(parentId).orElseThrow(InvalidRequestException::new);
        networkService.setParentNodesForHierarchy(nodeEntity);
        nodeEntity.setParent(parent);
        parent.getChildren().add(networkService.setParentNodesForHierarchy(nodeEntity));
        if (!validator.isValid(parent)) {
            throw new InvalidRequestException();
        }
        parent = nodeRepository.save(parent);
        log.info("Added new node to network successfully, network id :: {}", parent.getId());
        return parent;
    }

    @Override
    public NodeEntity updateNode(String id, NodeEntity nodeDetails) throws InvalidRequestException {
        NodeEntity node = networkService.setParentNodesForHierarchy(nodeRepository.findById(id).orElseThrow(InvalidRequestException::new));
        node.getChildren().clear();
        node.getChildren().addAll(nodeDetails.getChildren());
        node.setDescription(nodeDetails.getDescription());
        node.setName(nodeDetails.getName());
        node.setParams(nodeDetails.getParams());
        node.setType(nodeDetails.getType());
        if (!validator.isValid(node)) {
            throw new InvalidRequestException();
        }
        nodeRepository.save(node);
        log.info("Updated node successfully, id :: {}", id);
        return node;
    }

    @Override
    public NodeEntity getNodeById(String id) {
        NodeEntity nodeEntity = nodeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Node not found, id " + id));
        log.info("Returned node, id :: {}", id);
        return nodeEntity;
    }

    @Override
    public Map<String, Boolean> deleteNodeById(String id) {
        NodeEntity nodeEntity = nodeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Node not found, id  " + id));
        if (nodeEntity.getParent() != null) {
            NodeEntity parent = nodeEntity.getParent();
            parent.getChildren().remove(nodeEntity);
            nodeRepository.save(parent);
        } else {
            nodeRepository.deleteById(id);
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Deleted node successfully, id :: {}", id);
        return response;
    }

    @Override
    public List<NodeEntity> getAllNodes() {
        List<NodeEntity> nodes = nodeRepository.findAll();
        log.info("Returned all nodes successfully");
        return nodes;
    }
}
