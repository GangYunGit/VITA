package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.FriendApplyListDto;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSendingListDto;

import java.util.List;

public interface FriendService {

    List<FriendSendingListDto> getSendingFriendList(String userId);
//    List<FriendReceivingListDto> getReceivingFriendList(String userId);
    List<FriendApplyListDto> getApplyingFriendList(String userId);

    void acceptFriend();
    void rejectFriend();
}
