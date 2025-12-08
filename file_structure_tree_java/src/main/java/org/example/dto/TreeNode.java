package org.example.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@NoArgsConstructor
@JsonPropertyOrder({"id","name","path","type","childrenNodes"})
public class TreeNode {

    private UUID id;

    private String name;

    private String path;

    private String type;

    private List< TreeNode > childrenNodes;


    public List< TreeNode > getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List< TreeNode > childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId( UUID id ) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
