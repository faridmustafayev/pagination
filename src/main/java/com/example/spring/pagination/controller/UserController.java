package com.example.spring.pagination.controller;

import com.example.spring.pagination.model.criteria.PageCriteria;
import com.example.spring.pagination.model.criteria.UserCriteria;
import com.example.spring.pagination.model.request.CreateUserRequest;
import com.example.spring.pagination.model.response.PageableUserResponse;
import com.example.spring.pagination.model.response.UserResponse;
import com.example.spring.pagination.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addUser(@RequestBody CreateUserRequest request) {
        userService.addUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PatchMapping("/{id}/birth-place")
    public void setBirthPlace(@PathVariable Long id,
                              @RequestParam String birthPlace) {
        userService.setBirthPlace(id, birthPlace);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public PageableUserResponse<UserResponse> getUsers(PageCriteria pageCriteria,
                                                       UserCriteria userCriteria) {
        return userService.getUsers(pageCriteria, userCriteria);
    }

}
