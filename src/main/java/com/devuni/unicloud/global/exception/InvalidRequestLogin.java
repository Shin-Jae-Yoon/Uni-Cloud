package com.devuni.unicloud.global.exception;

import lombok.Getter;

@Getter
public class InvalidRequestLogin extends UniCloudException {

    private static final String MESSAGE = "아이디/비밀번호가 올바르지 않습니다.";

    public InvalidRequestLogin() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
