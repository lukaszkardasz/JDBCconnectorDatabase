package MongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByName (String name);

    @Query("{'name': ?0}") //nazwa z bazy danych
    List<Post> find (String name);

    @Query(value = "{'name': ?0}", fields = "{'message':1}") //nazwa z bazy danych tylko z jednym polem
    List<Post> findOnlyOneMessage (String name);
}
