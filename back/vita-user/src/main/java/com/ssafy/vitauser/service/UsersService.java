package com.ssafy.vitauser.service;

import com.ssafy.vitauser.entity.Users;

public interface UsersService {
    Users findByUserId(String userId);
}
