package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.*;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSendingListDto;
import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.entity.Users;
import com.ssafy.vitauser.repository.FriendRepository;
import com.ssafy.vitauser.repository.UsersRepository;
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
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FriendSendingListDto> getSendingFriendList(String userId) {
        // 친구 추가된 유저 == friendStatus가 "accepted"인 유저
        return friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
    }

    @Override
    public List<FriendReceivingListDto> getReceivingFriendList(String userId) {
        return friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "accepted");
    }

    @Override
    public List<FriendReceivingListDto> getApplyingFriendList(String userId) {
        return friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "applied");
    }

    @Override
    public List<FriendSearchMapping> getSearchFriendList(String userId, String userNickname) {
        return friendRepository.getFriendSearchList(userId, userNickname);
    }

    @Override
    public void applyFriend(String userId, String userNickname) {
        // userId = 내 아이디, userNickname = 신청 보낼 유저의 닉네임
        List<FriendSendingListDto> sendingUserList = friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
        List<FriendReceivingListDto> receivingUserList = friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userNickname, "accepted");

        FriendDto friend = new FriendDto();
        if (sendingUserList.isEmpty() && receivingUserList.isEmpty()) {
            friend.setFriendSendingUser(usersRepository.findByUserId(userId));
            friend.setFriendReceivingUser(usersRepository.findByUserNickname(userNickname));
            friend.setFriendStatus("applied");
        }
        friendRepository.save(friend.toEntity());
    }

    @Override
    public void acceptFriend(String sendingUserId, String receivingUserId, String friendStatus) {
        FriendDto currentRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userIdAndFriendStatus(sendingUserId, receivingUserId, friendStatus);
        System.out.println("-----------------");
        System.out.println(sendingUserId);
        System.out.println(receivingUserId);
        System.out.println(friendStatus);
        System.out.println("-----------------");
        System.out.println(currentRelation);
        currentRelation.setFriendStatus("accepted");
        friendRepository.save(currentRelation.toEntity());
    }

    @Override
    public void rejectFriend() {

    }
}
