package com.devuni.unicloud.domain.cloud.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Ram {

    MICRO("1GB", 0),
    SMALL("2GB", 500),
    MEDIUM("4GB", 1000),
    LARGE("8GB", 2000),
    X_LARGE("16GB", 4000),
    X2_LARGE("32GB", 8000),
    X3_LARGE("64GB", 16000);

    private final String ram;
    private final long dailyFee;

    public long getDailyFee() {
        return dailyFee;
    }
}
