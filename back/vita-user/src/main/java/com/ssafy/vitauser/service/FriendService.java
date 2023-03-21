package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.*;

import java.util.List;

public interface FriendService {

    List<FriendSendingListDto> getSendingFriendList(String userId);
    List<FriendReceivingListDto> getReceivingFriendList(String userId);
    List<FriendReceivingListDto> getApplyingFriendList(String userId);

    List<FriendSearchMapping> getSearchFriendList(String userId, String userNickname);

    void applyFriend(String userId, String userNickname);
    void acceptFriend(String sendingUserId, String receivingUserId, String friendStatus);
    void rejectFriend();
}
