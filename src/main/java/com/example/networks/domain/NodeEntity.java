package com.example.networks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@ToString(of = {"id","name","type","description"})
@Entity
@Table(name = "nodes")
public class NodeEntity {
    @Id
    private String id;
    private String type;
    private String name;
    private String description;
    @ElementCollection
    @MapKeyColumn(name = "param_name")
    @Column(name = "param_value")
    @CollectionTable(name = "node_params", joinColumns = @JoinColumn(name = "node_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Map<String, String> params;
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    private NodeEntity parent;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NodeEntity> children = new ArrayList<>();
}
