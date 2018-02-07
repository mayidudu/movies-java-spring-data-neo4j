package com.jn.rb.data.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 */
@SpringBootApplication
@EntityScan("com.jn.rb.data.process.domain")
public class DataProcessApp {

	public static void main(String[] args) {
		SpringApplication.run(DataProcessApp.class, args);
	}
}
