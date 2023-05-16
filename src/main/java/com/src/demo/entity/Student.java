package com.src.demo.entity;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dob;

    @NotBlank
    @Pattern(regexp = "[ABC]", message = "Section must be A, B or C")
    private String section;

    @NotBlank
    @Pattern(regexp = "[MF]")
    private String gender;

    @Min(0)
    @Max(100)
    private Integer marks1;

    @Min(0)
    @Max(100)
    private Integer marks2;

    @Min(0)
    @Max(100)
    private Integer marks3;

    private Integer total;

    private Double average;

    private String result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getMarks1() {
		return marks1;
	}

	public void setMarks1(Integer marks1) {
		this.marks1 = marks1;
	}

	public Integer getMarks2() {
		return marks2;
	}

	public void setMarks2(Integer marks2) {
		this.marks2 = marks2;
	}

	public Integer getMarks3() {
		return marks3;
	}

	public void setMarks3(Integer marks3) {
		this.marks3 = marks3;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", section=" + section + ", gender=" + gender + ", marks1=" + marks1 + ", marks2=" + marks2
				+ ", marks3=" + marks3 + ", total=" + total + ", average=" + average + ", result=" + result + "]";
	}
    

}

