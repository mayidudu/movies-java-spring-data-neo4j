package movies.spring.data.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by marvin on 2018/2/6.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class FacebookUser {
    @GraphId
    private Long id;

    private String screen_name;

    private String pathname;

    @Relationship(type = "IS_FRIEND_OF",direction = Relationship.UNDIRECTED)
    private Set<FacebookUser> friends;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addFriends(FacebookUser friend) {
        if (friends == null)
            friends = new HashSet<FacebookUser>();
        friends.add(friend);
    }

    public Set<FacebookUser> getFriends() {
        return friends;
    }

    public void setFriends(Set<FacebookUser> friends) {
        this.friends = friends;
    }
}
