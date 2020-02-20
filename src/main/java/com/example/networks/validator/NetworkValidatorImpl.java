package com.example.networks.validator;

import com.example.networks.domain.NodeEntity;
import com.example.networks.enums.NodeTypesEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class NetworkValidatorImpl implements Validator {
    @Override
    public boolean isValid(NodeEntity nodeEntity) {
        return validateHierarchy(nodeEntity) && validateUniqueness(nodeEntity);
    }

    private boolean validateHierarchy(NodeEntity nodeEntity) {
        boolean result = checkHierarchy(nodeEntity);
        if (!nodeEntity.getChildren().isEmpty()) {
            for (NodeEntity node :
                    nodeEntity.getChildren()) {
                result = result && validateHierarchy(node);
            }
        }
        return result;
    }

    private boolean validateUniqueness(NodeEntity nodeEntity) {
        List<String> idsList = new ArrayList<>();
        mapNodesToListOfIds(nodeEntity, idsList);
        return idsList.size() == new HashSet<>(idsList).size();
    }

    private List<String> mapNodesToListOfIds(NodeEntity nodeEntity, List<String> ids) {
        if (nodeEntity != null) {
            ids.add(nodeEntity.getId());
            List<NodeEntity> children = nodeEntity.getChildren();
            children.stream().filter(child -> child.getChildren() != null).forEach(child -> mapNodesToListOfIds(child, ids));
        }
        return ids;
    }

    private boolean checkHierarchy(NodeEntity nodeEntity) {
        switch (nodeEntity.getType()) {
            case "NETWORK":
                return nodeEntity.getParent() == null && nodeEntity.getChildren().stream().allMatch(s -> s.getType().equals(NodeTypesEnum.SUBSTATION.getValue()));
            case "SUBSTATION":
                return nodeEntity.getChildren().stream().allMatch(s -> s.getType().equals(NodeTypesEnum.TRANSFORMER.getValue()));
            case "TRANSFORMER":
                return nodeEntity.getChildren().stream().allMatch(s -> s.getType().equals(NodeTypesEnum.FEEDER.getValue()));
            case "FEEDER":
                return nodeEntity.getChildren().stream().allMatch(s -> s.getType().equals(NodeTypesEnum.RESOURCE.getValue()));
            case "RESOURCE":
                return nodeEntity.getChildren().isEmpty();
            default:
                return false;
        }
    }
}
