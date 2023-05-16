package com.src.demo.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.src.demo.entity.Student;
import com.src.demo.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        // Validate student fields
        if (student.getFirstName().length() < 3 || student.getLastName().length() < 3) {
            return ResponseEntity.badRequest().body("First name and last name must have at least 3 characters");
        }
        LocalDate now = LocalDate.now();
        LocalDate dob = student.getDob();
        Period age = Period.between(dob, now);
        if (age.getYears() < 16 || age.getYears() > 20) {
            return ResponseEntity.badRequest().body("DOB must be between 15 and 20 years ago");
        }
        if (!Arrays.asList("A", "B", "C").contains(student.getSection())) {
            return ResponseEntity.badRequest().body("Section must be A, B, or C");
        }
        if (!Arrays.asList("M", "F").contains(student.getGender())) {
            return ResponseEntity.badRequest().body("Gender must be M or F");
        }
        if (student.getMarks1() != null && (student.getMarks1() < 0 || student.getMarks1() > 100)) {
            return ResponseEntity.badRequest().body("Marks 1 must be between 0 and 100");
        }
        if (student.getMarks2() != null && (student.getMarks2() < 0 || student.getMarks2() > 100)) {
            return ResponseEntity.badRequest().body("Marks 2 must be between 0 and 100");
        }
        if (student.getMarks3() != null && (student.getMarks3() < 0 || student.getMarks3() > 100)) {
            return ResponseEntity.badRequest().body("Marks 3 must be between 0 and 100");
        }
        
        Student create = studentService.create(student);
        return ResponseEntity.ok(create);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudentMarks(@PathVariable Long id, @Valid @RequestBody Student student,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Optional<Student> optionalStudent = studentService.updateMarks(id, student);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalStudent);
    }

}
    
