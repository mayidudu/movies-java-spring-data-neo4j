package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.FacebookUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by marvin on 2018/2/6.
 */


@RepositoryRestResource(collectionResourceRel = "friends", path = "friends")
public interface FacebookUserRepository extends PagingAndSortingRepository<FacebookUser,Long> {
    @Query("MATCH (s:FacebookUser)<-[r:RELATED_WITH]-(e:FacebookUser) RETURN s,r,e LIMIT {limit}")
    Collection<FacebookUser> graph(@Param("limit") int limit);
}
