package com.example.userservice.db;

import com.example.userservice.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final SubsribeRepository subsribeRepository;

    public UserServiceImpl(UserRepository userRepository, SubsribeRepository subsribeRepository) {
        this.userRepository = userRepository;
        this.subsribeRepository = subsribeRepository;
    }

    @Override
    public void addUser(UserDTO userInfo) {
        UserEntity user = new UserEntity();
        user.setBirthDate(userInfo.getBirthDate());
        user.setStatus(userInfo.getStatus());
        user.setLogin(userInfo.getUsername());
        user.setFirstName(userInfo.getFirstName());
        user.setLastname(userInfo.getLastName());
        user.setGender(userInfo.getGender());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void addPhoto(String username, String filepath) {
        var user = userRepository.findById(username).orElseThrow();
        user.setPhotoUrl(filepath);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<ShortUserInfoDTO> findUsers(FindDTO find) {
        return userRepository.findByLoginLike("%"+find.getUsernamePart()+"%").stream().map(use->{
            ShortUserInfoDTO userDTO = new ShortUserInfoDTO();
            userDTO.setLastName(use.getLastname());
            userDTO.setUsername(use.getLogin());
            userDTO.setPhoto(use.getPhotoUrl());
            userDTO.setFirstName(use.getFirstName());
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void subscribe(SubscribeDTO publisher, String subscriber) {
        var subEntity = userRepository.getById(subscriber);
        var pubEntity = userRepository.getById(publisher.getUsername());
        var exist = subsribeRepository.findBySubscriberIdAndPublisherId(subEntity,pubEntity);
        if (exist==null){
            SubscribesEntity subscribes = new SubscribesEntity();
            subscribes.setSubscriberId(subEntity);
            subscribes.setPublisherId(pubEntity);
            subsribeRepository.saveAndFlush(subscribes);
        }
    }

    @Override
    public void unsubscribe(SubscribeDTO publisher, String subscriber) {
        var subEntity = userRepository.getById(subscriber);
        var pubEntity = userRepository.getById(publisher.getUsername());
        var exist = subsribeRepository.findBySubscriberIdAndPublisherId(subEntity,pubEntity);
        if (exist!=null){
            subsribeRepository.delete(exist);
        }
    }

    @Override
    public UserDTO getUserInfo(String user) {
        UserDTO userDTO = new UserDTO();
        var userEntity = userRepository.getById(user);
        userDTO.setGender(userEntity.getGender());
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setBirthDate(userEntity.getBirthDate());
        userDTO.setUsername(userEntity.getLogin());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastname());
        userDTO.setPhoto(userEntity.getPhotoUrl());
        return userDTO;
    }

    @Override
    public UserandSubStatusDTO getUserAndSubStatus(String publisher,String user) {
        UserandSubStatusDTO userandSubStatusDTO = new UserandSubStatusDTO();
        UserDTO userDTO = new UserDTO();
        var publisherEntity = userRepository.getById(publisher);
        userDTO.setGender(publisherEntity.getGender());
        userDTO.setStatus(publisherEntity.getStatus());
        userDTO.setBirthDate(publisherEntity.getBirthDate());
        userDTO.setUsername(publisherEntity.getLogin());
        userDTO.setFirstName(publisherEntity.getFirstName());
        userDTO.setLastName(publisherEntity.getLastname());
        userDTO.setPhoto(publisherEntity.getPhotoUrl());
        userandSubStatusDTO.setUser(userDTO);
        var userEntity = userRepository.getById(user);
        var exist = subsribeRepository.findBySubscriberIdAndPublisherId(userEntity,publisherEntity);
        if (exist != null){
            userandSubStatusDTO.setSubscibe(SubscribeStatus.SUBSCRIBE);
        }
        else {
            userandSubStatusDTO.setSubscibe(SubscribeStatus.UNSUBSCRIBE);
        }
        return userandSubStatusDTO;
    }

    @Override
    public List<ShortUserInfoDTO> getAllSubscribes(String subscriber) {
        var userEntity = userRepository.getById(subscriber);
        return subsribeRepository.findAllBySubscriberId(userEntity).stream().map(user->{
            ShortUserInfoDTO subescibes = new ShortUserInfoDTO();
            subescibes.setFirstName(user.getPublisherId().getFirstName());
            subescibes.setLastName(user.getPublisherId().getLastname());
            subescibes.setUsername(user.getPublisherId().getLogin());
            subescibes.setPhoto(user.getPublisherId().getPhotoUrl());
            return subescibes;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ShortUserInfoDTO> getAllSubscribers(String subscriber) {
        var userEntity = userRepository.getById(subscriber);
        return subsribeRepository.findAllByPublisherId(userEntity).stream().map(user->{
            ShortUserInfoDTO subescibers = new ShortUserInfoDTO();
            subescibers.setFirstName(user.getSubscriberId().getFirstName());
            subescibers.setLastName(user.getSubscriberId().getLastname());
            subescibers.setUsername(user.getSubscriberId().getLogin());
            subescibers.setPhoto(user.getSubscriberId().getPhotoUrl());
            return subescibers;
        }).collect(Collectors.toList());
    }
}
