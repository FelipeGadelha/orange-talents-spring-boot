package br.com.alura.forum.api.v1.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.api.v1.dto.request.TopicRq;
import br.com.alura.forum.api.v1.dto.request.TopicUpdateRq;
import br.com.alura.forum.api.v1.dto.response.TopicDetailsRs;
import br.com.alura.forum.api.v1.dto.response.TopicRs;
import br.com.alura.forum.domain.entity.Topic;
import br.com.alura.forum.domain.exception.EntityNotFoundException;
import br.com.alura.forum.domain.repository.CourseRepository;
import br.com.alura.forum.domain.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	public List<TopicRs> list(String courseName){
		if (courseName == null) {
			return TopicRs.convert(topicRepository.findAll());			
		}
		else {
			return TopicRs.convert(topicRepository.findByCourseName(courseName));
		}
	}
	
	@GetMapping("/{id}")
	public TopicDetailsRs findById(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		return new TopicDetailsRs(optional
				.orElseThrow(() -> new EntityNotFoundException(String.format("Não existe Tópico com o ID %d", id))));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicRs> save(@Valid @RequestBody TopicRq topicRq, UriComponentsBuilder uriBuilder) {
		var topic = topicRq.convert(courseRepository);
		Topic saved = topicRepository.save(topic);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicRs(saved));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicRs> update(@PathVariable Long id, @Valid @RequestBody TopicUpdateRq topicUpdateRq) {
		topicRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format("Não existe Tópico com o ID %d", id)));
		Topic topic = topicUpdateRq.update(id, topicRepository);
		return ResponseEntity.ok(new TopicRs(topic));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		topicRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format("Não existe Tópico com o ID %d", id)));
		topicRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
