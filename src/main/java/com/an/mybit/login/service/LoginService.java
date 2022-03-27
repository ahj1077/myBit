package com.an.mybit.login.service;

import com.an.mybit.user.dto.UserDTO;
import com.an.mybit.user.entity.UserEntity;
import com.an.mybit.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class LoginService {

    private UserService userService;

    public void login(UserDTO userDTO) throws RuntimeException {

        UserEntity userEntity = userService.retrieveUser(userDTO.getUserId());

        //유저 id가 존재하는지 검사
        if(userEntity == null){
            log.error("해당 id를 가진 유저가 없습니다.");
            throw new RuntimeException("해당 id를 가진 유저가 없습니다.");
        }

        //패스워드가 다른경우
        if(!userEntity.getPassword().equals(userDTO.getPassword())){
            log.error("패스워드가 틀립니다. userId = {}", userDTO.getUserId());
            throw new RuntimeException("패스워드가 틀립니다.");
        }

        //세션에 사용자 정보 insert

    }
}
