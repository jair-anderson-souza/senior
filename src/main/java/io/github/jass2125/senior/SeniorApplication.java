package io.github.jass2125.senior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SeniorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeniorApplication.class, args);
	}

}
