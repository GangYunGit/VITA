package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendReceivingListDto extends UserInfoDto {

    @Builder
    public FriendReceivingListDto(Friend friend) {
        this.userId = friend.getFriendSendingUser().getUserId();
        this.userNickname = friend.getFriendSendingUser().getUserNickname();
        this.userImg = friend.getFriendSendingUser().getUserImg();
        this.userScore = friend.getFriendReceivingUser().getUserScore();
        this.createdDate = friend.getFriendSendingUser().getCreatedDate();
    }
}
