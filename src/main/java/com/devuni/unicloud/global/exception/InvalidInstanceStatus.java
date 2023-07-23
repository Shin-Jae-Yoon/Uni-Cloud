package com.devuni.unicloud.global.exception;

public class InvalidInstanceStatus extends UniCloudException {

    private static final String MESSAGE = "이미 영구종료된 인스턴스입니다.";

    public InvalidInstanceStatus() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
