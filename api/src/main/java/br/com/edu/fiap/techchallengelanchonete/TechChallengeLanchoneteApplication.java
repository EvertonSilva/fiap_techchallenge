package br.com.edu.fiap.techchallengelanchonete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.edu.fiap"})
public class TechChallengeLanchoneteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengeLanchoneteApplication.class, args);
	}

}
