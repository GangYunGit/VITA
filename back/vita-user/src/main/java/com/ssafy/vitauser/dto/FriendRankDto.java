package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor

public class FriendRankDto {
    private String userNickname;
    private int userScore;

    public FriendRankDto(Friend friend) {
    }
}
