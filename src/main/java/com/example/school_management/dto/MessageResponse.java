package com.example.school_management.dto;

import java.util.Date;
import lombok.Data;

@Data

public class MessageResponse {

	private Integer statusCode;
	private Date timestamp;
	private String message;
	private String description;
	
	public MessageResponse(int statusCode, Date timestamp, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

}

