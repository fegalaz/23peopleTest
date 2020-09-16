package com.peopletest.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.peopletest.controller.dto.UserDto;
import com.peopletest.entity.AuthUser;
import com.peopletest.repository.UserRepository;
import com.peopletest.repository.UserRoleRepo;

@Service
public class AuthUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUser register(UserDto userDto) {
       // AuthUser authUser = new ObjectMapper().convertValue(userDto, AuthUser.class);
    	AuthUser entity = new AuthUser();
    	entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
    	entity.setRoles(Collections.singletonList(userRoleRepo.findByRoleNameContaining("USER")));
    	entity.setEmail(userDto.getEmail());
    	entity.setUsername(userDto.getUsername());
    	entity.setMobile(userDto.getMobile());
        Optional<AuthUser> optUser = userRepository.findByUsername(userDto.getUsername());
        
        if (!optUser.isPresent()) {
            return userRepository.save(entity);
        }
        throw new RuntimeException("User already exist");
    }
}
