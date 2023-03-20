package com.ssafy.vitauser.repository;

import com.ssafy.vitauser.dto.*;
import com.ssafy.vitauser.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
//    List<Friend> findAll();

    List<FriendSendingListDto> findByFriendSendingUser_userIdAndFriendStatus(String SendingUserId, String friendStatus);

    List<FriendApplyListDto> findByFriendReceivingUser_userIdAndFriendStatus(String ReceivingUserId, String friendStatus);

    List<FriendReceivingListDto> findByFriendReceivingUser_userNicknameAndFriendStatus(String userNickname, String friendStatus);

    @Query(value = "SELECT DISTINCT u.user_nickname, f.friend_status FROM users u " +
            "LEFT OUTER JOIN friend f ON (u.user_id = f.friend_sending_user_id OR u.user_id = f.friend_receiving_user_id) " +
            "AND (f.friend_sending_user_id = ?1 OR f.friend_receiving_user_id = ?1) AND f.friend_status = 'accepted'", nativeQuery = true
    )
    List<FriendSearchListDto> getFriendSearchList(String userId);

    FriendDto findByFriendSendingUserId(String userId);
    FriendDto save(FriendDto friend);

}
