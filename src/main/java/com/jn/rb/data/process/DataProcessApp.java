package com.jn.rb.data.process;

import com.jn.rb.data.process.config.SpringConfiguration;
import com.jn.rb.data.process.vertx.FacebookVerticle;
import io.vertx.core.Vertx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 */
@SpringBootApplication
@EntityScan("com.jn.rb.data.process.domain")
public class DataProcessApp {

	public static void main(String[] args) {
		//可以从获取context
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new FacebookVerticle(context));
		System.out.println("Deployment done");
		SpringApplication.run(DataProcessApp.class, args);
	}
}
