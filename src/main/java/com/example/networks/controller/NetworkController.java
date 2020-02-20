package com.example.networks.controller;

import com.example.networks.domain.NodeEntity;
import com.example.networks.exceptions.InvalidRequestException;
import com.example.networks.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/network")
public class NetworkController {
    @Autowired
    private NetworkService networkService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public NodeEntity createNetwork(@RequestBody NodeEntity nodeEntity) throws InvalidRequestException {
        return networkService.createNewNetwork(nodeEntity);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<NodeEntity> updateNetwork(@PathVariable(value = "id") String id,
                                                    @RequestBody NodeEntity networkDetails) throws InvalidRequestException {
        return ResponseEntity.ok(networkService.updateNetwork(id, networkDetails));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NodeEntity> getNetworkById(@PathVariable String id) {
        return ResponseEntity.ok().body(networkService.getNetworkById(id));
    }

    @RequestMapping(path = "/{id}/getChildren", method = RequestMethod.GET)
    public List<NodeEntity> getChildrenByNetworkId(@PathVariable String id) throws InvalidRequestException {
        return networkService.getChildrenByNetworkId(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteNetworkById(@PathVariable String id) {
        return networkService.deleteNetworkById(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<NodeEntity> getAllNetworks() {
        return networkService.getAllNetworks();
    }
}
