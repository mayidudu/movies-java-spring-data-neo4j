package com.jn.rb.data.process.repositories;

import com.jn.rb.data.process.controller.FacebookUserController;
import com.jn.rb.data.process.domain.FacebookUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void setUp() {
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setPathname("pathname");
        facebookUser.setScreenname("screen_name");
        facebookUser.setUid(1000l);
        facebookUserRepository.save(facebookUser);

        FacebookUser facebookUser2 = new FacebookUser();
        facebookUser2.setPathname("pathname2");
        facebookUser2.setScreenname("screen_name2");
        facebookUser2.setUid(2000l);
        facebookUserRepository.save(facebookUser2);

        FacebookUser facebookUser3 = new FacebookUser();
        facebookUser3.setPathname("pathname3");
        facebookUser3.setScreenname("screen_name3");
        facebookUser3.setUid(3000l);
        facebookUserRepository.save(facebookUser3);

        facebookUser.addFriends(facebookUser2);
        facebookUser2.addFriends(facebookUser);
        facebookUser2.addFriends(facebookUser3);
        facebookUser3.addFriends(facebookUser2);
        facebookUserRepository.save(facebookUser);
        facebookUserRepository.save(facebookUser2);
        facebookUserRepository.save(facebookUser3);

    }

    @After
    public void tearDown() {
        //session.purgeDatabase();

    }


    @Test
    public void testFacebookUser(){
        Map<String, Object> res =  facebookUserController.graph(100);
        System.out.println(res);
    }


    @Test
    public void testFacebookUserRepository(){
        FacebookUser facebookUser2 = facebookUserRepository.findByUid(1000l);
        Assert.assertTrue(facebookUser2 != null);
        System.out.println(facebookUser2.getScreenname());
    }
}
