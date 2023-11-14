package com.UserActivities01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UserActivities01.Test.Student;
import com.UserActivities01.Test.StudentRepo;

import jakarta.validation.Valid;

@RestController
public class SampleController {
	
	

	@Autowired
	 private StudentRepo studentRepository;

    @GetMapping("/hello")
    public Result sayHello() {
        Result result = new Result(true, 200, "Success", "Hello, World!");
        return result;
    }

    @GetMapping("/numbers")
    public Result numbers() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Number is " + i);
        }

        // Assuming the data field of Result should contain some meaningful data
        Result result = new Result(true, 200, "Success", "Numbers processed successfully");
        return result;
    }
    
    @PostMapping("/student/save")
    public ResponseEntity<Result> addStudent(@Valid @RequestBody Student student) {
        try {
            Student savedStudent = studentRepository.save(student);
            Result result = new Result(true, HttpStatus.OK.value(), "Student added successfully", savedStudent);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Result result = new Result(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to add student", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
