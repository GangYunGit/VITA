package com.ssafy.vitauser.dto;

import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendDto {

    private Long friendId;
    private String friendStatus;
    private Users friendSendingUser;
    private Users friendReceivingUser;

    // Dto를 이용하여 Entity 객체를 생성하는 방법. Entity에 Setter가 없기 때문에 이렇게 사용
    public Friend toEntity() {
        return Friend.builder()
                .friendStatus(friendStatus)
                .friendSendingUser(friendSendingUser)
                .friendReceivingUser(friendReceivingUser)
                .build();
    }
}
