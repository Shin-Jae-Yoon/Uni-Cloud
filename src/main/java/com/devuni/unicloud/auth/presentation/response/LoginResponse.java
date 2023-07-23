package com.devuni.unicloud.auth.presentation.response;

import com.devuni.unicloud.domain.user.domain.enums.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long userId;
    private Permission permission;

    public LoginResponse(Long userId, Permission permission) {
        this.userId = userId;
        this.permission = permission;
    }
}
