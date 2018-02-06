package movies.spring.data.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marvin on 2018/2/6.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uid")
@NodeEntity
public class FacebookUser {
    @GraphId
    private Long uid;

    private String screen_name;

    private String pathname;

    @Relationship(type = "RELATED_WITH")
    private List<FacebookUser> friends = new ArrayList<>();

    @Relationship(type = "RELATED_WITH", direction = Relationship.INCOMING)
    private List<FriendRole> roles = new ArrayList<>();

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<FacebookUser> getFriends() {
        return friends;
    }
    public void addRole(FriendRole role) {
        this.roles.add(role);
    }

    public List<FriendRole> getRoles() {
        return roles;
    }
}
