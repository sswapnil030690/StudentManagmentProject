package com.swapnil.student.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.student.dao.StudentRepository;
import com.swapnil.student.entity.Student;
import com.swapnil.student.exception.StudentAlreadyExistsExeption;
import com.swapnil.student.exception.StudentNotFoundException;
import com.swapnil.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentRepository;
	
	public Student addStudent(Student student) {
		Optional<Student> isStudentPresent = studentRepository.getStudentByNameAndMobileNumber(student.getName(),
				student.getMobileNumber());
		
		if(isStudentPresent.isPresent()) {
			throw new StudentAlreadyExistsExeption("Record with Name - " +student.getName() + " and Mobile number - " + student.getMobileNumber() + " already present");
		} 
		student.setIsFeePaid(false);
		return studentRepository.save(student);
	}
	
	public List<Student> addStudents(List<Student> students) {
		return studentRepository.saveAll(students);
	}

	@Override
	public List<Student> getStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent())
			return student.get();
		else
			throw new StudentNotFoundException("Student not found");
	}

}
