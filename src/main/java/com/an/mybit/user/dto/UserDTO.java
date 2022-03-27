package com.an.mybit.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    @NotEmpty(message = "userId는 필수입니다.")
    private String userId;

    @NotEmpty(message = "password는 필수입니다.")
    private String password;
}
