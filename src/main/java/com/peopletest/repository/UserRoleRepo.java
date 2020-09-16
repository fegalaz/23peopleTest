package com.peopletest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peopletest.entity.AuthRole;

public interface UserRoleRepo extends JpaRepository<AuthRole, Long> {
    AuthRole findByRoleNameContaining(String roleName);
}
