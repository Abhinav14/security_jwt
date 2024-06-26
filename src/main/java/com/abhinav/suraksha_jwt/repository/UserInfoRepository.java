package com.abhinav.suraksha_jwt.repository;

import com.abhinav.suraksha_jwt.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUsername(String username);
}
