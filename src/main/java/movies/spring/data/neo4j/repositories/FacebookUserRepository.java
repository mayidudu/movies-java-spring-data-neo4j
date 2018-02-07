package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.FacebookUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by marvin on 2018/2/6.
 */

@RepositoryRestResource(collectionResourceRel = "friends", path = "friends")
public interface FacebookUserRepository extends PagingAndSortingRepository<FacebookUser,Long> {
    @Query("MATCH (s:FacebookUser)<-[r:IS_FRIEND_OF]-(e:FacebookUser) RETURN s,r,e LIMIT {limit}")
    Collection<FacebookUser> graph(@Param("limit") int limit);

    FacebookUser findByUid(@Param("uid") Long uid);

    Collection<FacebookUser> findByScreen_NameLike(@Param("screen_name") String screen_name);

    Collection<FacebookUser> findByPathNameLike(@Param("pathname") String pathname);

}
