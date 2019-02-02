package hibernate;

import java.util.List;
import java.util.Optional;

public interface ZawodRepository {
    List<Zawod> findAll();
    Optional<Zawod> findById(int id);
    void save (Zawod zawod);
    void update(Zawod zawod);
    void delete (Zawod zawod);


}
