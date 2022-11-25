package com.example.sprinbootdemo;

import com.example.sprinbootdemo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
//@RestController
public class SprinbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprinbootdemoApplication.class, args);
		System.out.println("Hello");
	}
/*
	@GetMapping
	public List<Student> hello(){
		return List.of(
				new Student(
						1L,
						"Dedeepya",
						22,
						LocalDate.of(2000, Month.MAY,2),
						"dedeepya@gmail.com"
				)
		);
	}
*/
}
