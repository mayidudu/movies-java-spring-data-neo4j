package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.controller.FacebookUserController;
import movies.spring.data.neo4j.domain.FacebookUser;
import movies.spring.data.neo4j.domain.FriendRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by marvin on 2018/2/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class FacebookControllerTest {
    @Autowired
    private FacebookUserRepository facebookUserRepository;

    @Autowired
    private Session session;

    @Autowired
    private FacebookUserController facebookUserController;

    @Test
    public void testFacebookUser(){
        session.purgeDatabase();

        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setPathname("pathname");
        facebookUser.setScreen_name("screen_name");
        facebookUser.setUid(1000l);
        facebookUserRepository.save(facebookUser);

        FacebookUser facebookUser2 = new FacebookUser();
        facebookUser2.setPathname("pathname2");
        facebookUser2.setScreen_name("screen_name2");
        facebookUser2.setUid(2000l);
        facebookUserRepository.save(facebookUser2);

        facebookUser.addFriends(facebookUser2);
        facebookUser2.addFriends(facebookUser);
        facebookUserRepository.save(facebookUser);
        facebookUserRepository.save(facebookUser2);

         Map<String, Object> res =  facebookUserController.graph(100);
        System.out.println(res);
    }
}
