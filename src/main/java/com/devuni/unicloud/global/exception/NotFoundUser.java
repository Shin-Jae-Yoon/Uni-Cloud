package com.devuni.unicloud.global.exception;

public class NotFoundUser extends UniCloudException {

    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public NotFoundUser() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
