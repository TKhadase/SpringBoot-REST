package com.tushar.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.model.Student;
import com.tushar.model.Subjects;

@RestController
@RequestMapping("/Student")
public class StudentController {

	@GetMapping("/getDetails")
	public ResponseEntity<Student> getStudentDetails(){
		System.out.println("StudentController.getStudentDetails()");
		Student student = new Student(101,"Tushar", "PASS", 24, 
																		new String[] {"BANK", "TOURISM", "SCHOOL"},
																		Set.of(7798488512L, 7798488513L),
																		List.of(70.2, 80.3, 77.0),
																		Map.of("Adhar", "2143741852", "PAN", "AHG741D"),
																		new Subjects(1234, "JAVA"));
		
		
		HttpStatus status =  HttpStatus.OK;
		return new ResponseEntity<Student>(student, status);
	}
	
	@PostMapping("/saveDetails")
	public String getPostDetails(@RequestBody Student student  ){
	System.out.println("StudentController.getPostDetails()");
	return  student.toString();
	}
	
}	
