package com.ssafy.vitauser.service;

import com.ssafy.vitauser.entity.Users;
import com.ssafy.vitauser.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users findByUserId(String userId) {
        return usersRepository.findByUserId(userId);
    }
}
