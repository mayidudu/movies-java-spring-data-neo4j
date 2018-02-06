package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.controller.FacebookUserController;
import movies.spring.data.neo4j.domain.FacebookUser;
import movies.spring.data.neo4j.domain.FriendRole;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private FacebookUserController facebookUserController;

    @Test
    public void testFacebookUser(){
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setPathname("pathname");
        facebookUser.setScreen_name("screen_name");
        facebookUser.setUid(100000L);
        facebookUserRepository.save(facebookUser);

        FacebookUser facebookUser2 = new FacebookUser();
        facebookUser2.setPathname("pathname2");
        facebookUser2.setScreen_name("screen_name2");
        facebookUser2.setUid(200000L);
        facebookUserRepository.save(facebookUser2);

        FriendRole friendRole = new FriendRole();
        friendRole.setStart(facebookUser);
        friendRole.setEnd(facebookUser2);
        friendRole.addRoleName("friends");

        facebookUserRepository.save(facebookUser);
        facebookUserRepository.save(facebookUser2);

         Map<String, Object> res =  facebookUserController.graph(100);
        System.out.println(res);
    }
}
