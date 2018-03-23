package org.duc.coursemanager.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.duc.coursemanager.entity.Course;
import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	private static final Logger LOGGER = LogManager.getLogger(StudentService.class);

	@Autowired
	CourseRepository courseRepository;

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course getOne(int id) {
		return courseRepository.getOne(id);
	}

	public boolean add(Course course) {
		LOGGER.debug("add Course instance");
		try {
			courseRepository.save(course);
			LOGGER.debug("add successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("add failed", re);
			return false;
		}
	}

	public boolean edit(Course course) {
		LOGGER.debug("edit Course instance");
		try {
			courseRepository.saveAndFlush(course);
			LOGGER.debug("edit successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("edit failed", re);
			return false;
		}
	}

	public boolean deleteById(int id) {
		LOGGER.debug("delete Course instance");
		try {
			courseRepository.deleteById(id);
			LOGGER.debug("delete successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			return false;
		}
	}

	public boolean addStudent(Course course, Student student) {
		try {
			course.getStudents().add(student);
			courseRepository.saveAndFlush(course);
			LOGGER.debug("regist successful");
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("regist failed", re);
			return false;
		}
	}
}