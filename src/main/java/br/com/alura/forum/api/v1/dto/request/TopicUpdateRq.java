package br.com.alura.forum.api.v1.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.domain.entity.Topic;
import br.com.alura.forum.domain.repository.TopicRepository;

public class TopicUpdateRq {
	
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String title;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Topic update(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getById(id);
		topic.setTitle(title);
		topic.setMessage(message);
		return topic;
	}
	
}
