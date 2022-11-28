package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.User;
import com.nhnacademy.springjpa.domain.UserRegisterRequest;
import com.nhnacademy.springjpa.exception.UserRegisterFailedException;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.repository.UserRepository;
import com.nhnacademy.springjpa.validator.UserRegisterRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersRestController {
    private final UserRegisterRequestValidator validator;
    private final UserRepository userRepository;

    public UsersRestController(UserRegisterRequestValidator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User createUser(@Valid @RequestBody UserRegisterRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if (!userRepository.addUser(userRequest.getId(), userRequest.getPassword(), userRequest.getAge())) {
            throw new UserRegisterFailedException();
        }

        return userRepository.getUser(userRequest.getId());
    }

    @InitBinder("userRegisterRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
