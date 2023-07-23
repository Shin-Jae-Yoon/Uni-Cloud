package com.devuni.unicloud.global.exception;

public class NotFoundCloud extends UniCloudException {

    private static final String MESSAGE = "존재하지 않는 서버입니다.";

    public NotFoundCloud() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
