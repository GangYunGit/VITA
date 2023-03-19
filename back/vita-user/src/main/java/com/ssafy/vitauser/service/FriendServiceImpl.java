package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.FriendApplyListDto;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSendingListDto;
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
    public void acceptFriend() {

    }

    @Override
    public void rejectFriend() {

    }
}
