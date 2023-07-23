package com.devuni.unicloud.global.exception;

public class DuplicatedEmail extends UniCloudException {

    private static final String MESSAGE = "이미 가입된 아이디입니다.";

    public DuplicatedEmail() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
