package com.ssafy.vitauser.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface FriendSearchMapping {
    @JsonIgnore
    String getUser_Id();
    String getUser_Img();
    String getUser_Nickname();
    @JsonIgnore
    String getFriend_status();
}
