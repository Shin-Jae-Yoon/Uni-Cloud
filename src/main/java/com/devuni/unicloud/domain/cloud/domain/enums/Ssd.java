package com.devuni.unicloud.domain.cloud.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Ssd {

    MICRO("64GB", 0),
    SMALL("128GB", 200),
    MEDIUM("256GB", 400),
    LARGE("512GB", 800),
    X_LARGE("1TB", 1600),
    X2_LARGE("2TB", 3200),
    X3_LARGE("3TB", 6400);

    private final String ssd;
    private final long dailyFee;

    public long getDailyFee() {
        return dailyFee;
    }
}
