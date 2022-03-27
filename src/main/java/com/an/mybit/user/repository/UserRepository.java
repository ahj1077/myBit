package com.an.mybit.user.repository;

import com.an.mybit.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Repository
public class UserRepository {

    private Map<Integer, UserEntity> userStore  = new ConcurrentHashMap<>();
    private static AtomicInteger sequence = new AtomicInteger(3);

    UserRepository(){
        userStore.put(1, new UserEntity(1, "admin", "admin", "관리자", "안형준"));
        userStore.put(2, new UserEntity(2, "user1", "user1", "유저1", "유저1"));
        userStore.put(3, new UserEntity(3, "user2", "user2", "유저2", "유저2"));
    }

    public UserEntity findUserByUserId(String userId) throws RuntimeException {

        Optional<UserEntity> userEntityOptional = userStore.values().stream()
                .filter(entity -> userId.equals(entity.getUserId()))
                .findAny();

        if(userEntityOptional.isEmpty()){
            log.error("사용자를 찾을 수 없습니다.");
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        return userEntityOptional.get();
    }

    public void addUser(UserEntity userEntity) throws RuntimeException {

        if(isExistUserId(userEntity.getUserId())){
            log.error("이미 존재하는 userId 입니다.");
            throw new RuntimeException("이미 존재하는 userId 입니다.");
        }

        sequence.set(sequence.get() + 1);
        userEntity.setId(sequence.get());
        userStore.put(sequence.get(), userEntity);
    }

    private boolean isExistUserId(String userId){

        Optional<UserEntity> user = userStore.values().stream()
                .filter(entity -> userId.equals(entity.getUserId()))
                .findAny();

        if(user.isPresent())
            return true;
        else
            return false;
    }
}
