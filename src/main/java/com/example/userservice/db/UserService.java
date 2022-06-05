package com.example.userservice.db;

import com.example.userservice.dto.*;

import java.util.List;

public interface UserService {
    void addUser(UserDTO userInfo);
    void addPhoto(String username, String filepath);
    List<ShortUserInfoDTO> findUsers(FindDTO find);
    void subscribe(SubscribeDTO publisher,String subscriber);
    void unsubscribe(SubscribeDTO publisher,String subscriber);
    UserDTO getUserInfo(String user);
    UserandSubStatusDTO getUserAndSubStatus(String publisher,String user);
    List<ShortUserInfoDTO> getAllSubscribes(String subscriber);
    List<ShortUserInfoDTO> getAllSubscribers(String subscriber);
}
