package com.DrApp.FirstTry.AppointmentApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppointmentApplication {

	public static void main(String[] args) {


		SpringApplication.run(AppointmentApplication.class, args);

	}

}
