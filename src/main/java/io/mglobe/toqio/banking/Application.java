package io.mglobe.toqio.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.mglobe.toqio.banking")
public class Application {
	//public Logger logger = LogManager.getLogger(this.getClass());
	public static ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		
		context = SpringApplication.run(Application.class, args);
		
	}

}
