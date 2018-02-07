package com.jn.rb.data.process.repositories;

import com.jn.rb.data.process.domain.User;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marvin on 2018/2/6.
 */
@Repository
public interface UserRepository extends GraphRepository<User>{

}
