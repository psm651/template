package com.example.template.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class ApiResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meta meta;
    private Object data;

    public static ApiResponse ofObject(Object object) {
        return ApiResponse.builder().meta(null).data(object).build();
    }

    public static ApiResponse ofPage(Page page) {
        PageResponse pageResponse = PageResponse.builder()
                .limit(page.getSize())
                .offset((long) page.getNumber() * page.getSize())
                .total(page.getTotalElements())
                .build();
        return ApiResponse.builder()
                .data(page.getContent())
                .meta(Meta.builder()
                        .page(pageResponse)
                        .build())
                .build();
    }

    @Getter
    @Builder
    static class Meta {
        private final PageResponse page;
    }

    @Getter
    @Builder
    static class PageResponse {
        private final int limit;
        private final long offset;
        private final long total;
    }
}
