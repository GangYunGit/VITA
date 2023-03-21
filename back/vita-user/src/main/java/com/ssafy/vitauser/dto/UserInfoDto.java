package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class UserInfoDto {

    protected String userNickname;
    protected String userId;
    protected String userImg;
    protected int userScore;
    protected String userHistoryImg;
    protected ZonedDateTime createdDate;

    UserInfoDto(Users users) {
        this.userNickname = users.getUserNickname();
        this.userId = users.getUserId();
        this.userImg = users.getUserImg();
        this.userScore = users.getUserScore();
        this.createdDate = users.getCreatedDate();
    }

}
