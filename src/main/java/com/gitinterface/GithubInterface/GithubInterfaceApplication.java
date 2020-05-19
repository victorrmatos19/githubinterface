package com.gitinterface.GithubInterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubInterfaceApplication.class, args);
	}

}
