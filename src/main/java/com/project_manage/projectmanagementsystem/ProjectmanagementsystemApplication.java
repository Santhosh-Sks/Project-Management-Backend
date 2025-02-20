package com.project_manage.projectmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project_manage.projectmanagementsystem")
public class ProjectmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectmanagementsystemApplication.class, args);
	}

}
