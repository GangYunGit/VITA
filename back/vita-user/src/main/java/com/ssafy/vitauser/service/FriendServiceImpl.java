package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.*;
import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.repository.FriendRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepository friendRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FriendSendingListDto> getSendingFriendList(String userId) {
        // 친구 추가된 유저 == friendStatus가 "accepted"인 유저
        return friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
    }

//    @Override
//    public List<FriendReceivingListDto> getReceivingFriendList(String userId) {
//        return friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "accepted");
//    }

    @Override
    public List<FriendApplyListDto> getApplyingFriendList(String userId) {
        return friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "applied");
    }

    @Override
    public List<FriendSearchListDto> getSearchFriendList(String userId) {
        return friendRepository.getFriendSearchList(userId);
    }

    @Override
    public void applyFriend(String userId, String userNickname) {
        // userId = 내 아이디, userNickname = 신청 보낼 유저의 닉네임
        FriendDto friend = friendRepository.findByFriendSendingUserId(userId);
        List<FriendSendingListDto> sendingUser = friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
        List<FriendReceivingListDto> recevingUser = friendRepository.findByFriendReceivingUser_userNicknameAndFriendStatus(userNickname, "accepted");

        if (sendingUser.isEmpty() && recevingUser.isEmpty()) {
            friend = new FriendDto();
            friend.setFriendSendingUserId(userId);
            friend.setFriendReceivingUserId("4");
            friend.setFriendStatus("applied");
        }

        friendRepository.save(friend);
    }

    @Override
    public void acceptFriend() {

    }

    @Override
    public void rejectFriend() {

    }
}
