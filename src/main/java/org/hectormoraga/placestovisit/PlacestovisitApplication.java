package org.hectormoraga.placestovisit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PlacestovisitApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PlacestovisitApplication.class, args);
	}

	/*
	 *  SpringBootServletInitializer is only needed if you are building
	 *  a war file and deploying it.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlacestovisitApplication.class);
	}
}
