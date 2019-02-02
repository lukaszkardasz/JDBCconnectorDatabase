package hibernate;

import java.util.List;

public interface ZawodRepository {
    List<Zawod> findAll();
    Zawod findById(int id);
    void save (Zawod zawod);
    void update(Zawod zawod);
    void delete (Zawod zawod);


}
