package com.an.mybit.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserEntity {

    private Integer id;
    private String userId;
    private String password;
    private String nickname;
    private String userName;
}
