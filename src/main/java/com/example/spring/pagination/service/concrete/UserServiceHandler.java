package com.example.spring.pagination.service.concrete;

import com.example.spring.pagination.aop.annotation.Log;
import com.example.spring.pagination.dao.entity.UserEntity;
import com.example.spring.pagination.dao.repository.UserRepository;
import com.example.spring.pagination.exception.NotFoundException;
import com.example.spring.pagination.model.criteria.PageCriteria;
import com.example.spring.pagination.model.criteria.UserCriteria;
import com.example.spring.pagination.model.request.CreateUserRequest;
import com.example.spring.pagination.model.response.PageableUserResponse;
import com.example.spring.pagination.model.response.UserResponse;
import com.example.spring.pagination.service.abstraction.UserService;
import com.example.spring.pagination.service.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.example.spring.pagination.mapper.UserMapper.USER_MAPPER;
import static com.example.spring.pagination.model.enums.ExceptionConstant.USER_NOT_FOUND;
import static com.example.spring.pagination.model.enums.UserStatus.DELETED;

@Log
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;

    @Override
    public void addUser(CreateUserRequest request) {
        userRepository.save(USER_MAPPER.buildUserEntity(request));
    }

    @Override
    public UserResponse getUser(Long id) {
        var user = fetchUserIfExist(id);
        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public void setBirthPlace(Long id, String birthPlace) {
        var user = fetchUserIfExist(id);
        USER_MAPPER.updateBirthPlace(user, birthPlace);
        userRepository.save(user);
    }

    @Override
    public PageableUserResponse<UserResponse> getUsers(PageCriteria pageCriteria,
                                                       UserCriteria userCriteria) {
        var users = userRepository.findAll(
                UserSpecification.of(userCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending()));

        return USER_MAPPER.mapPageableUserResponse(users);
    }

    @Override
    public void deleteUser(Long id) {
        var userEntity = fetchUserIfExist(id);
        userEntity.setStatus(DELETED);
        userRepository.save(userEntity);
    }

    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage()));
    }
}
