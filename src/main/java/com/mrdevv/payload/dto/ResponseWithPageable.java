package com.mrdevv.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"content", "pageable"})
public record ResponseWithPageable<T>(
        @JsonProperty("content")
        T data,
        PageableData pageable
) {
}
