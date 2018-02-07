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

    private String screenname;

    private Long uid;

    private String pathname;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Relationship(type = "IS_FRIEND_OF",direction = Relationship.UNDIRECTED)
    private Set<FacebookUser> friends;


    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public Long getId() {
        return id;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public void addFriends(FacebookUser friend) {
        if (friends == null)
            friends = new HashSet<FacebookUser>();
        boolean existed=false;
        for(FacebookUser item:friends){
            if (item.getUid() == friend.getUid()) {
                existed = true;
                break;
            }
        }
        if(!existed)
            friends.add(friend);
    }

    public Set<FacebookUser> getFriends() {
        return friends;
    }

    public void setFriends(Set<FacebookUser> friends) {
        this.friends = friends;
    }
}
