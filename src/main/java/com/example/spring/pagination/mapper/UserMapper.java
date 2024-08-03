package com.example.spring.pagination.mapper;

import com.example.spring.pagination.dao.entity.UserEntity;
import com.example.spring.pagination.model.request.CreateUserRequest;
import com.example.spring.pagination.model.response.PageableUserResponse;
import com.example.spring.pagination.model.response.UserResponse;
import org.springframework.data.domain.Page;

import static com.example.spring.pagination.model.enums.UserStatus.ACTIVE;
import static com.example.spring.pagination.model.enums.UserStatus.IN_PROGRESS;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(CreateUserRequest request) {
        return UserEntity.builder()
                .username(request.getUsername())
                .age(request.getAge())
                .birthplace(request.getBirthplace())
                .status(ACTIVE)
                .build();
    }

    public UserResponse buildUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .age(user.getAge())
                .birthplace(user.getBirthplace())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .status(user.getStatus())
                .build();
    }

    public PageableUserResponse<UserResponse> mapPageableUserResponse(Page<UserEntity> userPage) {

        var userResponses = userPage.stream()
                .map(USER_MAPPER::buildUserResponse)
                .toList();

        return PageableUserResponse.<UserResponse>builder()
                .users(userResponses)
                .hasNextPage(userPage.hasNext())
                .totalElements(userPage.getTotalElements())
                .lastPageNumber(userPage.getTotalPages())
                .build();
    }

    public void updateBirthPlace(UserEntity user, String birthplace) {
        user.setStatus(IN_PROGRESS);
        user.setBirthplace(birthplace);
    }

}
