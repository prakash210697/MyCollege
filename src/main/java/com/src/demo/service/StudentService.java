package com.src.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.src.demo.entity.Student;
import com.src.demo.repo.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student create(Student student) {

		String result = "Fail";
		if (student.getMarks1() != null && student.getMarks1() >= 35 && student.getMarks2() != null
				&& student.getMarks2() >= 35 && student.getMarks3() != null && student.getMarks3() >= 35) {
			result = "Pass";
		}

		Integer total = (student.getMarks1() != null ? student.getMarks1() : 0)
				+ (student.getMarks2() != null ? student.getMarks2() : 0)
				+ (student.getMarks3() != null ? student.getMarks3() : 0);
		Double average = total / 3.0;

		student.setTotal(total);
		student.setAverage(average);
		student.setResult(result);

		return studentRepository.save(student);
	}

	public Optional<Student> updateMarks(Long id, Student studentMark) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
		
		student.setMarks1(studentMark.getMarks1());
		student.setMarks2(studentMark.getMarks2());
		student.setMarks3(studentMark.getMarks3());

		Integer total = (studentMark.getMarks1() != null ? studentMark.getMarks1() : 0)
				+ (studentMark.getMarks2() != null ? studentMark.getMarks2() : 0)
				+ (studentMark.getMarks3() != null ? studentMark.getMarks3() : 0);
		Double average = total / 3.0;

		String result = "Fail";
		if (studentMark.getMarks1() != null && studentMark.getMarks1() >= 35 && studentMark.getMarks2() != null
				&& studentMark.getMarks2() >= 35 && studentMark.getMarks3() != null && studentMark.getMarks3() >= 35) {
			result = "Pass";
		}

		student.setTotal(total);
		student.setAverage(average);
		student.setResult(result);
		
		student  = studentRepository.save(student);
		Optional<Student> optStudent = Optional.of(student);
		return optStudent;
	}

}
