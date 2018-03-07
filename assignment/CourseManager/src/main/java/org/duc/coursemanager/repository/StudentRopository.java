package org.duc.coursemanager.repository;

import org.duc.coursemanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRopository extends JpaRepository<Student, Integer> {

}