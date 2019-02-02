package SpringDataJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
class SpringApp {

    public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication
            .run(SpringApp.class, args);

        CarsService p = context.getBean(
                CarsService.class);
        //p.test();
        //p.checkName();
        //p.checkNameAndEngine();
        p.checkByEngine();
        p.test();
        p.test2();



    }
}
