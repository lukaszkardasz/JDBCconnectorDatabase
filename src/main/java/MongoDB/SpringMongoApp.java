package MongoDB;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
class SpringMongoApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication
                .run(SpringMongoApp.class, args);
        PostRepository bean = context.getBean(PostRepository.class);
        Post s = new Post();
        s.setMessage("Hej");
        s.setName("Franek");
        bean.insert(s);

        Post s1 = new Post();
        s1.setMessage("Ziomus tweet");
        s1.setName("Dupawolowa");
        bean.insert(s1);

        for (Post post : bean.findAll()){
            System.out.println(post);
        }

        for (Post post : bean.findOnlyOneMessage("Dupawolowa")){
            System.out.println(post);
        }

    }
}
