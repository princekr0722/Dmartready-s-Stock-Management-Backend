package com.dmart.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	private String message;
    private List<String> errors = new ArrayList<>();

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

	public ErrorResponse(int status, String message) {
		this.message = String.valueOf(status);
		this.errors.add(message);
	}

}
