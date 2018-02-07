package com.jn.rb.data.process.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by marvin on 2018/2/6.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="rid")
@RelationshipEntity(type = "RELEATED_WITH")
public class FriendRole {

    @GraphId
    private Long rid;

    private Collection<String> roles = new ArrayList<>();

    @StartNode
    private FacebookUser start;

    @EndNode
    private FacebookUser end;

    public void addRoleName(String name) {
        this.roles.add(name);
    }

    public FacebookUser getStart() {
        return start;
    }

    public void setStart(FacebookUser start) {
        this.start = start;
    }

    public FacebookUser getEnd() {
        return end;
    }

    public void setEnd(FacebookUser end) {
        this.end = end;
    }
}
