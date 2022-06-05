package com.example.userservice.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubsribeRepository extends JpaRepository<SubscribesEntity,Long> {
    SubscribesEntity findBySubscriberIdAndPublisherId(UserEntity subscriber, UserEntity publisher);
    List<SubscribesEntity> findAllBySubscriberId(UserEntity userEntity);
    List<SubscribesEntity> findAllByPublisherId(UserEntity userEntity);

}
