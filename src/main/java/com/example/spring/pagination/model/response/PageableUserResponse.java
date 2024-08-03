package com.example.spring.pagination.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PageableUserResponse<T> {
    private List<T> users;
    private int lastPageNumber;
    private long totalElements;
    private boolean hasNextPage;
}
