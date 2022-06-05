package com.example.userservice.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<UserEntity,String> {
    List<UserEntity> findByLoginLike(String login);
}
