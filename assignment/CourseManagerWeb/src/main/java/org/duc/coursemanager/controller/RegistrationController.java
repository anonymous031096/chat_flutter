package org.duc.coursemanager.controller;

import org.duc.coursemanager.entity.Course;
import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.service.CourseService;
import org.duc.coursemanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("duc/registration")
public class RegistrationController {

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@RequestMapping(value = "regist/{idStudent}", method = RequestMethod.PUT)
	public ResponseEntity<Void> regist(@PathVariable("idStudent") int idStudent,
			@RequestParam(value = "idCourse") int idCourse) {
		Student student = studentService.getOne(idStudent);
		if (student == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		Course course = courseService.getOne(idCourse);
		if (course == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		studentService.regisCourse(student, course);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "add/{idCourse}", method = RequestMethod.PUT)
	public ResponseEntity<Void> add(@PathVariable("idCourse") int idCourse,
			@RequestParam(value = "idStudent") int idStudent) {
		Student student = studentService.getOne(idStudent);
		if (student == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		Course course = courseService.getOne(idCourse);
		if (course == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		courseService.addStudent(course, student);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
