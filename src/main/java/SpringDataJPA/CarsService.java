package SpringDataJPA;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
class CarsService {
    private final CarsRepository carsRepository;

    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Transactional
    public void test() {
        Cars s = new Cars();
        s.setName("Polonez");
        s.setProductionDate(LocalDate.now().minusYears(15));
        s.setEngine("disel 2.0 tdi");
        s.setType("caro");
        carsRepository.save(s);
        Cars s1 = new Cars();
        s1.setName("Opel");
        s1.setProductionDate(LocalDate.now().minusYears(6));
        s1.setEngine("disel 2.0 tdi");
        s1.setType("corsa");
        carsRepository.save(s1);
        Iterable<Cars> all = carsRepository.findAll();
        for (Cars cars : all) {
            System.out.println(cars);
        }
    }

    public void checkName() {
        Iterable<Cars> all = carsRepository.findAllByNameEquals("Opel");
        for (Cars cars : all) {
            System.out.println(cars);
        }
    }

    public void checkNameAndEngine() {
        Iterable<Cars> all = carsRepository.findAllByNameEqualsAndTypeEquals("Polonez", "caro");
        for (Cars cars : all) {
            System.out.println(cars);
        }
    }

    public void checkByEngine() {
        Cars s = new Cars();
        s = carsRepository.findFirstByEngine("disel 2.0 tdi"); //szuka jednego - tylko pierwszego
        System.out.println(s);
    }

    @Transactional()
    public void test2() {
        List<Cars> polonez = carsRepository.find("Polonez", "disel 2.0 tdi");
        for (Cars cars : polonez) {
            System.out.println(cars);
        }
    }

}