package com.mrdevv.payload.mapper;

import com.mrdevv.payload.dto.PageableData;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageableData toPageableData(Page page){
        return new PageableData(
                page.getPageable().getPageNumber(),
                page.isLast(),
                page.isFirst(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getNumberOfElements(),
                page.getPageable().getOffset()
        );
    }
}
