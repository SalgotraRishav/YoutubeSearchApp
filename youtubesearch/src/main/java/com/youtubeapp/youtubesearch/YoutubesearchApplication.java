// src/main/java/com/youtubeapp/youtubesearch/YoutubeSearchApplication.java
package com.youtubeapp.youtubesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This annotation enables Spring Boot's auto-configuration and component scanning.
public class YoutubesearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubesearchApplication.class, args);
	}

}