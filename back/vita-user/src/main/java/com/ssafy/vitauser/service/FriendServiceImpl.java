package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.FriendListDto;
import com.ssafy.vitauser.dto.FriendListInterface;
import com.ssafy.vitauser.entity.Users;
import com.ssafy.vitauser.repository.FriendRepository;
import com.ssafy.vitauser.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FriendListInterface> getMyFriendList(String userId) {
        System.out.println("유저 목록");
        return friendRepository.findMyFriendList(userId);
    }



    @Override
    public void acceptFriend() {

    }

    @Override
    public void rejectFriend() {

    }
}
