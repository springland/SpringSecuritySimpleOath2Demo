package com.springland365.SpringSecuritySimpleOath2Demo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class SpringSecuritySimpleOath2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecuritySimpleOath2DemoApplication.class, args);
	}

}
