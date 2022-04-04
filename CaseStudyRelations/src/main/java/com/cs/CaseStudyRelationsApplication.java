package com.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages= {"address","cart"})
@ComponentScan
public class CaseStudyRelationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyRelationsApplication.class, args);
	}

}
