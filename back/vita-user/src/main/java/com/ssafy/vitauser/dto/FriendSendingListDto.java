package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendSendingListDto{

    private String userNickname;
    private String userId;
    private String userImg;
    private String userHistoryImg;
    private ZonedDateTime createdDate;

    public FriendSendingListDto(Friend friend) {
        this.userId = friend.getFriendReceivingUser().getUserId();
        this.userNickname = friend.getFriendReceivingUser().getUserNickname();
        this.userImg = friend.getFriendReceivingUser().getUserImg();
        this.createdDate = friend.getFriendReceivingUser().getCreatedDate();
    }
}
