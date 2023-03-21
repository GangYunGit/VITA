package com.ssafy.vitauser.repository;

import com.ssafy.vitauser.dto.*;
import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
//    List<Friend> findAll();

    @Query(value = "SELECT DISTINCT u.user_id, u.user_nickname, u.user_img, f.friend_status FROM users u " +
            "LEFT OUTER JOIN friend f ON (u.user_id = f.friend_sending_user_id OR u.user_id = f.friend_receiving_user_id) " +
            "AND (f.friend_sending_user_id = ?1 OR f.friend_receiving_user_id = ?1) AND f.friend_status = 'accepted' " +
            "WHERE u.user_nickname LIKE %?2% "
            , nativeQuery = true
    )
    List<FriendSearchMapping> getFriendSearchList(String userId, String userNickname);

    Friend save(Friend friend);
    List<FriendSendingListDto> findByFriendSendingUser_userIdAndFriendStatus(String sendingUserId, String friendStatus);
    List<FriendReceivingListDto> findByFriendReceivingUser_userIdAndFriendStatus(String receivingUserId, String friendStatus);
    FriendDto findByFriendSendingUser_userIdAndFriendReceivingUser_userIdAndFriendStatus(String sendingUserId, String receivingUserId, String friendStatus);

}
