package com.ssafy.vitauser.repository;

import com.ssafy.vitauser.dto.FriendListDto;
import com.ssafy.vitauser.dto.FriendListInterface;
import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
    List<Friend> findAll();

    @Query(value = "SELECT * " +
            "FROM friend f, users u " +
            "WHERE f.friend_sending_user_id = ?1 AND f.friend_status = 'accepted'", nativeQuery = true)
    List<FriendListInterface> findMyFriendList(String myUserId);
}
