package SpringDataJPA;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
class CarsService {
    private final CarsRepository carsRepository;

    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

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

    public void checkName(){
        Cars s = new Cars();
        Iterable<Cars> all = carsRepository.findAllByNameEquals("Opel");
        for (Cars cars : all) {
            System.out.println(cars);
        }
    }

    public void checkNameAndEngine(){
        Cars s = new Cars();
        Iterable<Cars> all = carsRepository.findAllByNameEqualsAndTypeEquals("Polonez", "caro");
        for (Cars cars : all) {
            System.out.println(cars);
        }
    }

}