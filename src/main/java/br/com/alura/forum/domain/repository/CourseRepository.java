package br.com.alura.forum.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.domain.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	Course findByName(String courseName);

}
