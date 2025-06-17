package com.swapnil.student.service;

import java.util.List;

import com.swapnil.student.entity.Student;
import com.swapnil.student.exception.StudentAlreadyExistsExeption;

public interface StudentService {
	
	Student addStudent(Student student) throws StudentAlreadyExistsExeption;
	
	List<Student> addStudents(List<Student> students);

	List<Student> getStudents();
	
	Student getStudentById(Long studentId);
	

}
