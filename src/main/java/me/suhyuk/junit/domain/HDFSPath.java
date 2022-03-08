package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table()
@SuperBuilder
public class HDFSPath extends BasePath {

    public HDFSPath() {
        super();
    }

    @Column(name = "NAME_NODE", length = 50) private String nameNode;

    public String getNameNode() {
        return nameNode;
    }

    public void setNameNode(String nameNode) {
        this.nameNode = nameNode;
    }
}