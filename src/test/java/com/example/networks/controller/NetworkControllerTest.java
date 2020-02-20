package com.example.networks.controller;

import com.example.networks.domain.NodeEntity;
import com.example.networks.enums.NodeTypesEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NetworkController.class)
class NetworkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NetworkController networkController;

//    @Test
//    void createNetwork() throws Exception {
//        NodeEntity nodeEntity = new NodeEntity();
//        nodeEntity.setId("nt-test");
//        nodeEntity.setName("test-nt");
//        nodeEntity.setType(NodeTypesEnum.NETWORK.getValue());
//        given(networkController.createNetwork(nodeEntity)).willReturn(nodeEntity);
//        String json = new ObjectMapper().writeValueAsString(nodeEntity);
//        mockMvc.perform(post("/api/network")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(json))
//                .andExpect(status().isOk());
//    }

    @Test
    void updateNetwork() {
    }

    @Test
    void getNetworkById() {
    }

    @Test
    void getChildrenByNetworkId() {
    }

    @Test
    void deleteNetworkById() {
    }

    @Test
    void getAllNetworks() {
    }
}