package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendListDto {
    private String userNickname;
    private String userId;
    private String userImg;
    private String userHistoryImg;
    private ZonedDateTime createdDate;
}
