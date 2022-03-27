package com.an.mybit.user.service;

import com.an.mybit.user.entity.UserEntity;
import com.an.mybit.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserEntity retrieveUser(String userId) throws RuntimeException {

        return userRepository.findUserByUserId(userId);
    }

}
