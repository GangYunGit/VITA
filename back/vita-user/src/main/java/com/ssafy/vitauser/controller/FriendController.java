package com.ssafy.vitauser.controller;

import com.ssafy.vitauser.dto.FriendApplyListDto;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSearchListDto;
import com.ssafy.vitauser.dto.FriendSendingListDto;
import com.ssafy.vitauser.entity.Friend;
import com.ssafy.vitauser.service.FriendService;
import com.ssafy.vitauser.service.UsersService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private FriendService friendService;
    private UsersService usersService;
    private ModelMapper modelMapper;

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

    @PostMapping
    public void applyFriend(@RequestHeader HttpHeaders headers, @RequestBody String userNickname) throws Exception {
        friendService.applyFriend(headers.getFirst("userID"), userNickname);
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

    @GetMapping("/apply")
    public ResponseEntity<?> searchFriendList(@RequestHeader HttpHeaders headers) {
        try {
            List<FriendSearchListDto> friendList = friendService.getSearchFriendList(headers.getFirst("userID"));
            System.out.println("asdasd");
            if (friendList != null && !friendList.isEmpty()) {
                return new ResponseEntity<List<FriendSearchListDto>>(friendList, HttpStatus.OK);
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
