package laurent.spring_elastic;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringElasticApplication {
    @Autowired
    private Indexer indexer;

    public static void main(String[] args) {
        SpringApplication.run(SpringElasticApplication.class, args);
    }

    @PostConstruct
    public void init() {
        indexer.indexAllAlbums();
    }
}
