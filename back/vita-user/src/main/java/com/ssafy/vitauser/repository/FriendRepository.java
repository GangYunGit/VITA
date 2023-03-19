package com.ssafy.vitauser.repository;

import com.ssafy.vitauser.dto.FriendApplyListDto;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSendingListDto;
import com.ssafy.vitauser.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
//    List<Friend> findAll();

    List<FriendSendingListDto> findByFriendSendingUser_userIdAndFriendStatus(String SendingUserId, String friendStatus);

    List<FriendApplyListDto> findByFriendReceivingUser_userIdAndFriendStatus(String ReceivingUserId, String friendStatus);
}
