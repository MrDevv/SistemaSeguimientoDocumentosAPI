package com.mrdevv.payload.dto;

public record PageableData(
        Integer numberPage,
        Boolean lastPage,
        Boolean firstPage,
        Integer totalPages,
        Long totalElements,
        Integer numberOfElements,
        Long offset
) {
}
