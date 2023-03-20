package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendDto {
    private Long friendId;
    private String friendStatus;
    private String friendSendingUserId;
    private String friendReceivingUserId;
    FriendDto(Friend friend) {
        this.friendId = friend.getFriendId();
        this.friendStatus = friend.getFriendStatus();
        this.friendSendingUserId = friend.getFriendSendingUser().getUserId();
        this.friendReceivingUserId = friend.getFriendReceivingUser().getUserId();
    }
}
