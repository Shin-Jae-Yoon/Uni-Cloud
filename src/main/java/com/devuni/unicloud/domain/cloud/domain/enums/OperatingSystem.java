package com.devuni.unicloud.domain.cloud.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperatingSystem {

    WINDOWS("windows"),
    MAC_OS("macOS"),
    UBUNTU("ubuntu"),
    DEBIAN("debian");

    private final String operatingSystem;
}
