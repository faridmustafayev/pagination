package com.example.spring.pagination.model.response;

import com.example.spring.pagination.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private Integer age;
    private String birthplace;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserStatus status;
}
