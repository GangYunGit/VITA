package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepo extends JpaRepository<UserHistory,Long> {
    List<UserHistory> findByUser_UserId(String userId);
}
