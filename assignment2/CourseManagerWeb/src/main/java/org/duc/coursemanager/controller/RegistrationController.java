package org.duc.coursemanager.controller;

import org.duc.coursemanager.entity.Course;
import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.service.CourseService;
import org.duc.coursemanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("duc/registration")
public class RegistrationController {

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> regist(@RequestParam(value = "idStudent") int idStudent,
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
}
