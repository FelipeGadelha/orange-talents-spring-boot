package br.com.alura.forum.api.v1.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.domain.entity.Topic;

public class TopicRs {
	
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	
	public TopicRs(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public static List<TopicRs> convert(List<Topic> topics) {
		return topics.stream().map(TopicRs::new).collect(Collectors.toList());
	}
	
}
