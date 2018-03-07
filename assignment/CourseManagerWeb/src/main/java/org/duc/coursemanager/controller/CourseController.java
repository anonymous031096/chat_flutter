package org.duc.coursemanager.controller;

import java.util.Date;
import java.util.List;

import org.duc.coursemanager.entity.Course;
import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/duc/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Course>> listAllCities() {
		List<Course> courses = courseService.findAll();
		if (courses.isEmpty())
			return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> getById(@PathVariable("id") int id) {
		Course course = courseService.getOne(id);
		if (course == null)
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestParam(value = "name") String name,
			@RequestParam(value = "timeStart") Date timeStart, @RequestParam(value = "timeEnd") Date timeEnd,
			UriComponentsBuilder ucBuilder) {
		Course student = new Course(name, timeStart, timeEnd);
		courseService.add(student);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("{id}").buildAndExpand(student.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Course> edit(@PathVariable("id") int id, @RequestParam(value = "name") String name,
			@RequestParam(value = "timeStart") Date timeStart, @RequestParam(value = "timeEnd") Date timeEnd) {
		Course course = courseService.getOne(id);
		if (course == null)
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		course.setName(name);
		course.setTimeStart(timeStart);
		course.setTimeEnd(timeEnd);
		courseService.edit(course);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Student> delete(@PathVariable("id") int id) {
		Course course = courseService.getOne(id);
		if (course == null)
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		courseService.deleteById(id);
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
}