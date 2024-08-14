package laurent.spring_elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringElasticApplication.class, args);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    public String getMessage() {
        return "Coucou";
    }

}
