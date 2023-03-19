package com.ssafy.vitauser.controller;

import com.ssafy.vitauser.dto.FriendApplyListDto;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSendingListDto;
import com.ssafy.vitauser.service.FriendService;
import com.ssafy.vitauser.service.UsersService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private FriendService friendService;
    private UsersService usersService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> friendList(@RequestHeader HttpHeaders headers) {
        try {
//            Users user = usersService.findByUserId(headers.getFirst("userID"));
            List<FriendSendingListDto> friendList = friendService.getSendingFriendList(headers.getFirst("userID"));
            System.out.println("asdasd");
            if (friendList != null && !friendList.isEmpty()) {
                return new ResponseEntity<List<FriendSendingListDto>>(friendList, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/applyList")
    public ResponseEntity<?> applyingFriendList(@RequestHeader HttpHeaders headers) {
        try {
//            Users user = usersService.findByUserId(headers.getFirst("userID"));
            List<FriendApplyListDto> friendList = friendService.getApplyingFriendList(headers.getFirst("userID"));
            System.out.println("asdasd");
            if (friendList != null && !friendList.isEmpty()) {
                return new ResponseEntity<List<FriendApplyListDto>>(friendList, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }


    private ResponseEntity<?> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
