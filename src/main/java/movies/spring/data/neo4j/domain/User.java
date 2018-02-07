package movies.spring.data.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.cypher.query.SortOrder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marvin on 2018/2/6.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uid")
@NodeEntity          // #1 This class is backed by a Neo4j Node
public class User {
    @GraphId
    private Long uid;

    private String screen_name;

    private String pathname;

    @Relationship(type = "IS_FRIEND_OF",direction = Relationship.UNDIRECTED)
    private Set<User> friends;

    public void addFriends(User friend) {
        if (friends == null)
            friends = new HashSet<User>();
        friends.add(friend);
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

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

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
