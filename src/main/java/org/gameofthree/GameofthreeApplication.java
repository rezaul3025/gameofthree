package org.gameofthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class GameofthreeApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(GameofthreeApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("index");
	}
}
