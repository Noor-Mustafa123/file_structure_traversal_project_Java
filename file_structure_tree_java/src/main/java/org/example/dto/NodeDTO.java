package org.example.dto;

import java.util.List;

public class NodeDTO {

    public String id;

    public String name;

    public String type;

    public List< NodeDTO > nodeDTOList;

    public NodeDTO( String id, String name, String type, List< NodeDTO > nodeDTOList ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.nodeDTOList = nodeDTOList;
    }

    public List< NodeDTO > getNodeDTOList() {
        return nodeDTOList;
    }

    public void setNodeDTOList( List< NodeDTO > nodeDTOList ) {
        this.nodeDTOList = nodeDTOList;
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

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

}
