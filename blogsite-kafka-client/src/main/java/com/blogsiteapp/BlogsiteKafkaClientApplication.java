package com.blogsiteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BlogsiteKafkaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsiteKafkaClientApplication.class, args);
	}

}
