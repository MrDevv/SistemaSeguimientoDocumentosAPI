package com.mrdevv.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"status", "code", "url", "method", "message", "backend_message", "timestamp", "details"})
public record ResponseError (
        String status,
        @JsonProperty("code") int httCode,
        String url,
        @JsonProperty("method") String httpMethod,
        String message,
        @JsonProperty("backend_message") String backendMessage,
        LocalDateTime timestamp,
        List<String> details
) {
}
