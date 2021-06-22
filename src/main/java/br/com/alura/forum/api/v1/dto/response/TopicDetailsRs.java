package br.com.alura.forum.api.v1.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.domain.entity.StatusTopic;
import br.com.alura.forum.domain.entity.Topic;

public class TopicDetailsRs {
	
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	private String authorName;
	private StatusTopic status;
	private List<ResponseRs> responses;
	
	public TopicDetailsRs(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
		this.authorName = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.responses = new ArrayList<>();
		this.responses.addAll(
				topic.getResponses()
					.stream()
					.map(ResponseRs::new)
					.collect(Collectors.toList()));
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

	public String getAuthorName() {
		return authorName;
	}

	public StatusTopic getStatus() {
		return status;
	}

	public List<ResponseRs> getResponses() {
		return responses;
	}
	
}
