package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendReceivingListDto {

    private String userNickname;
    private String userId;
    private String userImg;
    private String userHistoryImg;
    private ZonedDateTime createdDate;

    public FriendReceivingListDto(Friend friend) {
        this.userId = friend.getFriendSendingUser().getUserId();
        this.userNickname = friend.getFriendSendingUser().getUserNickname();
        this.userImg = friend.getFriendSendingUser().getUserImg();
        this.createdDate = friend.getFriendSendingUser().getCreatedDate();
    }
}
