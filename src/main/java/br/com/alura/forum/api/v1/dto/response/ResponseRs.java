package br.com.alura.forum.api.v1.dto.response;

import java.time.LocalDateTime;

import br.com.alura.forum.domain.entity.Response;

public class ResponseRs {
	
	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String authorName;
	
	public ResponseRs(Response response) {
		this.id = response.getId();
		this.message = response.getMessage();
		this.creationDate = response.getCreationDate();
		this.authorName = response.getAuthor().getName();
	}

	public Long getId() {
		return id;
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
	
}
