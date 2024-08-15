package laurent.spring_elastic;

import org.springframework.boot.SpringApplication;

public class TestSpringElasticApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringElasticApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
