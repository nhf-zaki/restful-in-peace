package nhfzaki.restfulinpeace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author nhf-zaki on 12/18/18
 */
@EnableJpaAuditing
@SpringBootApplication
public class RestfulInPeaceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestfulInPeaceApplication.class, args);
    }

}