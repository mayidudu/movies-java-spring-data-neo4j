package movies.spring.data.neo4j.services;

import movies.spring.data.neo4j.domain.FacebookUser;
import movies.spring.data.neo4j.domain.FriendRole;
import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.repositories.FacebookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by marvin on 2018/2/6.
 */
@Service
public class FacebookService {
    @Autowired
    private FacebookUserRepository facebookUserRepository;

    private Map<String, Object> toD3Format(Collection<FacebookUser> friends) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        Iterator<FacebookUser> result = friends.iterator();
        while(result.hasNext()){
            FacebookUser start=result.next();
            nodes.add(map("title",start.getScreen_name(),"label","facebookuser"));
            int target=i;
            i++;
            for(FacebookUser user:start.getFriends()){
                Map<String,Object> friend = map("title",user.getScreen_name(),"label","facebookuser");
                int source = nodes.indexOf(friend);
                if(source == -1){
                    nodes.add(friend);
                    source=i++;
                }
                rels.add(map("source",source,"target",target));
            }
        }

        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }
    @Transactional(readOnly = true)
    public Map<String, Object> graph(int limit) {
        Collection<FacebookUser> result = facebookUserRepository.graph(limit);
        System.out.println("======graph=====");
        System.out.println(result);
        return toD3Format(result);
    }
}
