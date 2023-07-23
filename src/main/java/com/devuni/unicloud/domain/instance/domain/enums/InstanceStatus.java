package com.devuni.unicloud.domain.instance.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstanceStatus {

    READY("실행준비"),
    RUNNING("실행중"),
    STOPPED("정지"),
    TERMINATED("영구종료");

    private final String instanceStatus;
}
