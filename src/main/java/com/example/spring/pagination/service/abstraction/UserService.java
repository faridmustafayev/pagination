package com.example.spring.pagination.service.abstraction;

import com.example.spring.pagination.model.criteria.PageCriteria;
import com.example.spring.pagination.model.criteria.UserCriteria;
import com.example.spring.pagination.model.request.CreateUserRequest;
import com.example.spring.pagination.model.response.PageableUserResponse;
import com.example.spring.pagination.model.response.UserResponse;

public interface UserService {
    void addUser(CreateUserRequest request);

    UserResponse getUser(Long id);

    void setBirthPlace(Long id, String birthPlace);

    PageableUserResponse<UserResponse> getUsers(PageCriteria pageCriteria,
                                                UserCriteria userCriteria);

    void deleteUser(Long id);
}
