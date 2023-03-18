package com.ssafy.vitauser.repository;

import com.ssafy.vitauser.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findAll();
}
