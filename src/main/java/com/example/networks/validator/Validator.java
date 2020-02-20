package com.example.networks.validator;
import com.example.networks.domain.NodeEntity;

public interface Validator {
    boolean isValid(NodeEntity nodeEntity);
}
