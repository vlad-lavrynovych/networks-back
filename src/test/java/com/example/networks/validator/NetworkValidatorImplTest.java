package com.example.networks.validator;

import com.example.networks.domain.NodeEntity;
import com.example.networks.enums.NodeTypesEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetworkValidatorImpl.class)
class NetworkValidatorImplTest {
    @Autowired
    private Validator networkValidatorImpl;

    private NodeEntity network;

    @BeforeEach
    void setUp() {
        network = new NodeEntity();
    }

    @Test
    void shouldBeValidWhenNetworkIsValid() {
        network.setId("test-1");
        network.setType(NodeTypesEnum.NETWORK.getValue());
        NodeEntity child = new NodeEntity();
        child.setId("child-1");
        child.setType(NodeTypesEnum.SUBSTATION.getValue());
        assertTrue(networkValidatorImpl.isValid(network));
    }

    @Test
    void shouldBeInvalidWhenNodeHierarchyInvalid() {
        network.setId("test-1");
        network.setType(NodeTypesEnum.SUBSTATION.getValue());
        NodeEntity child = new NodeEntity();
        child.setType(NodeTypesEnum.NETWORK.getValue());
        child.setId("test-2");
        network.getChildren().add(child);
        assertFalse(networkValidatorImpl.isValid(network));
    }

    @Test
    void shouldBeInvalidWhenIdNotUnique() {
        network.setId("test-1");
        network.setType(NodeTypesEnum.NETWORK.getValue());
        NodeEntity child = new NodeEntity();
        child.setType(NodeTypesEnum.SUBSTATION.getValue());
        child.setId("test-1");
        network.getChildren().add(child);
        assertFalse(networkValidatorImpl.isValid(network));
    }
}