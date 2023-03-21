package com.ssafy.vitauser.service;

import com.ssafy.vitauser.entity.Users;

import java.util.List;

public interface UsersService {
    Users findByUserId(String userId);
    List<Users> findAll();
}
