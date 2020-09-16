package com.peopletest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.peopletest.controller.dto.UserDto;
import com.peopletest.entity.AuthUser;
import com.peopletest.service.AuthUserService;

@RestController
@RequestMapping("/oauth/users")
public class UserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthUser register(@RequestBody UserDto userDto) {
        return authUserService.register(userDto);
    }

}
