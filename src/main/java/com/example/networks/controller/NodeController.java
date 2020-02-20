package com.example.networks.controller;

import com.example.networks.domain.NodeEntity;
import com.example.networks.exceptions.InvalidRequestException;
import com.example.networks.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/node")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<NodeEntity> createNode(@PathVariable(value = "id") String parentId,
                                                 @RequestBody NodeEntity nodeEntity) throws InvalidRequestException {
        return ResponseEntity.ok().body(nodeService.createNewNode(parentId, nodeEntity));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<NodeEntity> updateNode(@PathVariable(value = "id") String id,
                                                    @RequestBody NodeEntity nodeDetails) throws InvalidRequestException {
        return ResponseEntity.ok(nodeService.updateNode(id, nodeDetails));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NodeEntity> getNodeById(@PathVariable String id) {
        return ResponseEntity.ok().body(nodeService.getNodeById(id));
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteNodeById(@PathVariable String id) {
        return ResponseEntity.ok().body(nodeService.deleteNodeById(id));
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<NodeEntity>> getAllNodes() {
        return ResponseEntity.ok().body(nodeService.getAllNodes());
    }
}
