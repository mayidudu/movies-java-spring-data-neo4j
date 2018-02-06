package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.controller.MovieController;
import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Person;
import movies.spring.data.neo4j.domain.Role;
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
public class MovieControllerTest {

    @Autowired
    private MovieController movieController;

    @Autowired
    private MovieRepository instance;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Session session;

    @Test
    public void testMovieController(){
        Movie matrix = new Movie("The Matrix", 1999);
        instance.save(matrix);



        Person keanu = new Person("Keanu Reeves");
        personRepository.save(keanu);


        Role neo = new Role(matrix, keanu);
        neo.addRoleName("Neo");



        matrix.addRole(neo);
        instance.save(matrix);

        Map<String, Object> res = movieController.graph(100);
        System.out.println(res);

        session.purgeDatabase();
    }

}
