package org.example.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class NodeDTO {

    private UUID id;

    private String name;

    private String path;

    private String type;

    private List< NodeDTO > childrenNodes;


    public List< NodeDTO > getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List< NodeDTO > childrenNodes) {
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
