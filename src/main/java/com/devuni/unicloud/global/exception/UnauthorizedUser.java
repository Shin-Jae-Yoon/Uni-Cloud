package com.devuni.unicloud.global.exception;

public class UnauthorizedUser extends UniCloudException {

    private static final String MESSAGE = "인증되지 않은 회원입니다. 로그인이 필요합니다.";

    public UnauthorizedUser() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
