package SpringDataJPA;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends CrudRepository<Cars, Long> {

    //tworzymy swoje metody np wyszukiwania
    List<Cars> findAllByNameEqualsAndTypeEquals(String name, String type);
    Cars findFirstByEngine(String engine);
    List<Cars> findAllByNameEquals(String name);

    @Query("select c from Cars c where c.name = ?1 and c.engine =?2")
    List<Cars> find(String name, String engine);

    @Query ("from Cars where name = :name1 and engine = :engine1")
    List<Cars> find2(@Param("name1")String name, @Param("engine1")String engine);


}
