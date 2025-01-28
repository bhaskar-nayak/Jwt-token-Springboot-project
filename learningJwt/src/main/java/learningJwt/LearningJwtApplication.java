package learningJwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.learningJwt")
@EntityScan(basePackages = {"com.learningJwt.model"})
@SpringBootApplication
public class LearningJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningJwtApplication.class, args);
	}

}
