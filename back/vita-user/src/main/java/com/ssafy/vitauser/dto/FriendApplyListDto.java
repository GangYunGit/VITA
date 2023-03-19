package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendApplyListDto {
    private String userNickname;
    private String userId;

    public FriendApplyListDto(Friend friend) {
        this.userNickname = friend.getFriendSendingUser().getUserNickname();
        this.userId = friend.getFriendSendingUser().getUserId();
    }
}
