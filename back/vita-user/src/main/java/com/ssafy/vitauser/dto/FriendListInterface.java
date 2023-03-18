package com.ssafy.vitauser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface FriendListInterface {
    String getUser_nickname();
    String getFriend_receiving_user_id();
    String getUser_img();
    String getUsers_history_img();

    LocalDateTime getCreated_date();
}
