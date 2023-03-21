package com.ssafy.vitauser.controller;

import com.ssafy.vitauser.dto.FriendApplyListDto;
import com.ssafy.vitauser.dto.FriendReceivingListDto;
import com.ssafy.vitauser.dto.FriendSearchMapping;
import com.ssafy.vitauser.dto.FriendSendingListDto;
import com.ssafy.vitauser.dto.FriendRankDto;
import com.ssafy.vitauser.dto.UserInfoDto;
import com.ssafy.vitauser.entity.Users;
import com.ssafy.vitauser.service.FriendService;
import com.ssafy.vitauser.service.UsersService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

    // 친구 목록 GET
    @GetMapping
    public ResponseEntity<?> friendList(@RequestHeader HttpHeaders headers) {
        try {
            // 친구를 담을 리스트를 <친구리스트 부모 클래스 형>으로 선언
            List<UserInfoDto> myFriendList = new ArrayList<>();

            // header에서 받은 userId에 해당하는 유저의 받은 친구, 보낸 친구를 각각 리스트에 담기
            List<?> ReceivingFriendList = friendService.getReceivingFriendList(headers.getFirst("userID"));
            List<?> SendingFriendList = friendService.getSendingFriendList(headers.getFirst("userID"));
            for(Object
                    friend : SendingFriendList) {
                myFriendList.add((UserInfoDto) friend);
            }
            for(Object
                    friend : ReceivingFriendList) {
                myFriendList.add((UserInfoDto) friend);
            }
            if (myFriendList != null && !myFriendList.isEmpty()) {
                return ResponseEntity.ok().body(myFriendList);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping
    public void applyFriend(@RequestHeader HttpHeaders headers, @RequestBody Map<String, String> req) throws Exception {
        System.out.println(req);
        friendService.applyFriend(headers.getFirst("userID"), req.get("user_nickname"));
    }

    @PutMapping
    public void acceptFriend(@RequestHeader HttpHeaders suId, @RequestHeader HttpHeaders ruId) throws Exception {
        System.out.println(suId.getFirst("sendingUserId"));
        System.out.println(ruId.getFirst("receivingUserId"));
        friendService.acceptFriend(suId.getFirst("sendingUserId"), ruId.getFirst("receivingUserId"), "applied");
    }

    // 친구 신청온 리스트 GET
    @GetMapping("/applyList")
    public ResponseEntity<?> applyingFriendList(@RequestHeader HttpHeaders headers) {
        try {
            List<?> applyingFriendList = friendService.getApplyingFriendList(headers.getFirst("userID"));
            System.out.println("asdasd");
            if (applyingFriendList != null && !applyingFriendList.isEmpty()) {
                return new ResponseEntity<List<?>>(applyingFriendList.stream().map(friend -> modelMapper.map(friend, FriendApplyListDto.class)).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

   
    // 친구 신청 검색(친구로 등록이 안되어 있는 유저만 모두 보여주기) GET
    @GetMapping("/apply/{nickname}")
    public ResponseEntity<?> searchFriendList(@RequestHeader HttpHeaders headers, @PathVariable("nickname") String nickname) {
        try {
            System.out.println("-----------------------------------------------");
            System.out.println(nickname);
            String getUserId = headers.getFirst("userID");
            List<FriendSearchMapping> usersList = friendService.getSearchFriendList(getUserId, nickname);
            usersList.removeIf(users -> users.getUser_Id().equals(getUserId));
            usersList.removeIf(users -> Objects.equals(users.getFriend_status(), "accepted"));

            if (usersList != null && !usersList.isEmpty()) {
                return ResponseEntity.ok().body(usersList);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }


    // 친구 랭킹 GET
    @GetMapping("/rank")
    public ResponseEntity<?> rankFriendList(@RequestHeader HttpHeaders headers) {
        try {
            // 친구를 담을 리스트를 <친구리스트 부모 클래스 형>으로 선언
            Users myInfo = usersService.findByUserId(headers.getFirst("userID"));

            // 랭킹에 나도 포함 시킴, modelMapper로 타입 맞춰서 넣기
            List<UserInfoDto> rankFriendList = new ArrayList<>();
            rankFriendList.add(modelMapper.map(myInfo, UserInfoDto.class));

            // header에서 받은 userId에 해당하는 유저의 받은 친구, 보낸 친구를 각각 리스트에 담기
            List<FriendReceivingListDto> ReceivingFriendList = friendService.getReceivingFriendList(headers.getFirst("userID"));
            List<FriendSendingListDto> SendingFriendList = friendService.getSendingFriendList(headers.getFirst("userID"));
            for(Object
                    friend : SendingFriendList) {
                rankFriendList.add((UserInfoDto) friend);
            }
            for(Object
                    friend : ReceivingFriendList) {
                rankFriendList.add((UserInfoDto) friend);
            }

            // 최종 출력
            if (rankFriendList != null && !rankFriendList.isEmpty()) {
                return new ResponseEntity<List<?>>(rankFriendList.stream().map(friend -> modelMapper.map(friend, FriendRankDto.class)).collect(Collectors.toList()), HttpStatus.OK);
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
