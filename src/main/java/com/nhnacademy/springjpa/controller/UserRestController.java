package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.User;
import com.nhnacademy.springjpa.domain.UserModifyRequest;
import com.nhnacademy.springjpa.exception.UserModifyFailedException;
import com.nhnacademy.springjpa.exception.UserNotFoundException;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.repository.UserRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/users/{userId}")
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute(value = "user", binding = false)
    public User getUser(@PathVariable("userId") String userId) {
        User user = userRepository.getUser(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @GetMapping
    public User getUser(@ModelAttribute("user") User user) {
        return user;
    }

    //modelAttribute : 메서드 레벨에서 사용할 때 get,post매핑이 되기 전에 select를 통해 user객체가 먼저 생성된다?
    @PutMapping
    public User modifyUser(@ModelAttribute("user") User user,
                           @Valid @RequestBody UserModifyRequest request,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if (!userRepository.modifyUser(user.getId(), request.getPassword())) {
            throw new UserModifyFailedException();
        }

        return userRepository.getUser(user.getId());
    }

}
