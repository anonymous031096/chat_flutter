package org.duc.coursemanager.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.duc.coursemanager.entity.Course;
import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.repository.StudentRopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private static final Logger LOGGER = LogManager.getLogger(StudentService.class);

	@Autowired
	StudentRopository studentReposity;

	public List<Student> findAll() {
		return studentReposity.findAll();
	}

	public Student getOne(int id) {
		return studentReposity.getOne(id);
	}

	public boolean add(Student student) {
		LOGGER.debug("add Student instance");
		try {
			studentReposity.save(student);
			LOGGER.debug("add successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("add failed", re);
			return false;
		}
	}

	public boolean edit(Student student) {
		LOGGER.debug("edit Student instance");
		try {
			studentReposity.saveAndFlush(student);
			LOGGER.debug("edit successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("edit failed", re);
			return false;
		}
	}

	public boolean deleteById(int id) {
		LOGGER.debug("delete Student instance");
		try {
			studentReposity.deleteById(id);
			LOGGER.debug("delete successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			return false;
		}
	}
	
	public boolean regisCourse(Student student, Course course) {
		try {
		student.getCourses().add(course);
		studentReposity.saveAndFlush(student);
		LOGGER.debug("regist successful");
		return true;
		}catch (RuntimeException re) {
			LOGGER.error("regist failed", re);
			return false;
		}
	}
}