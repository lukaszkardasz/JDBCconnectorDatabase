package SpringDataJPA;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends CrudRepository<Cars, Long> {

    //tworzymy swoje metody np wyszukiwania
    List<Cars> findAllByNameEqualsAndTypeEquals(String name, String type);
    Cars findByEngine(String engine);
    List<Cars> findAllByNameEquals(String name);

}
