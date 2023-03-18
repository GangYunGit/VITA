package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.FriendListDto;
import com.ssafy.vitauser.dto.FriendListInterface;
import com.ssafy.vitauser.entity.Users;

import java.util.List;

public interface FriendService {

    List<FriendListInterface> getMyFriendList(String userId);

    void acceptFriend();
    void rejectFriend();
}
