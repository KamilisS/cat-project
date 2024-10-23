package com.example.cat_project.Core;

public class ErrorResponse {
    private final String errorMessage;

    public ErrorResponse(String message) {
        this.errorMessage = message;
    }

    public String getMessage() {
        return errorMessage;
    }
}
