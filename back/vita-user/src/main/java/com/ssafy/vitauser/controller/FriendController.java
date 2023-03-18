package com.ssafy.vitauser.controller;

import com.ssafy.vitauser.dto.FriendListDto;
import com.ssafy.vitauser.dto.FriendListInterface;
import com.ssafy.vitauser.entity.Users;
import com.ssafy.vitauser.service.FriendService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> friendList(@RequestHeader HttpHeaders headers) {
        try {
            List<FriendListInterface> friendList = friendService.getMyFriendList(headers.getFirst("userID"));
            System.out.println("asdasd");
            if (friendList != null) {
                return new ResponseEntity<List<FriendListInterface>>(friendList, HttpStatus.OK);
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
