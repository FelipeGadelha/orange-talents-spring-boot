package br.com.alura.forum.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.domain.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

	List<Topic> findByCourseName(String courseName);
	
}
