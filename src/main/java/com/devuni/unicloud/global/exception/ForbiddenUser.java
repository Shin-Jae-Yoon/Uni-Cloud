package com.devuni.unicloud.global.exception;

public class ForbiddenUser extends UniCloudException {

    private static final String MESSAGE = "권한이 허용되지 않은 회원입니다.";

    public ForbiddenUser() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
