package org.duc.coursemanager.controller;

import java.util.List;

import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/duc/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student>> listAll() {
		List<Student> students = studentService.findAll();
		if (students.isEmpty())
			return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getById(@PathVariable("id") int id) {
		Student student = studentService.getOne(id);
		if (student == null)
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
		studentService.add(student);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("{id}").buildAndExpand(student.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Student> edit(@RequestBody Student student, @PathVariable("id") int id) {
		Student cStudent = studentService.getOne(id);
		if (student == null)
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		cStudent.setFirstName(student.getFirstName());
		cStudent.setLastName(student.getLastName());
		cStudent.setGender(student.getGender());
		cStudent.setAddress(student.getAddress());
		studentService.edit(cStudent);
		return new ResponseEntity<Student>(cStudent, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Student> delete(@PathVariable("id") int id) {
		Student student = studentService.getOne(id);
		if (student == null)
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		studentService.deleteById(id);
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
}