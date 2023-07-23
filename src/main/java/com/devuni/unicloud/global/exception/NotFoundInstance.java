package com.devuni.unicloud.global.exception;

public class NotFoundInstance extends UniCloudException {

    private static final String MESSAGE = "존재하지 않는 인스턴스입니다.";

    public NotFoundInstance() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
