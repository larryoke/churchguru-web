package com.laotek.churchguru;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = VelocityAutoConfiguration.class)
@Configuration
@ServletComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {

	// String workingDir = System.getProperty("user.dir");
	// System.out.println("Current working directory : " + workingDir);
	// SpringApplication.run(Application.class, args);

	SpringApplication application = new SpringApplication(Application.class);

	Properties properties = new Properties();
	properties.load(
		new FileInputStream(System.getProperty("user.home") + "/.churchguru-deploy/application.properties"));
	application.setDefaultProperties(properties);

	application.run(args);
    }
}
