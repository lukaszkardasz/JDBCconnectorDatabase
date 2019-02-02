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

    @Query("from Cars where name = ?1 and engine =?2")
    List<Cars> find(String name, String engine);

    @Query ("from Cars where name = :name and engine = :engine")
    List<Cars> find2(@Param("name")String name, @Param("engine")String engine);


}
