package com.epac.billingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BillinngApp implements CommandLineRunner {
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(BillinngApp.class, args);
	}
	@Override
	public void run(String... args) throws Exception {}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
