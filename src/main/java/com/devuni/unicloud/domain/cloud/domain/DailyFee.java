package com.devuni.unicloud.domain.cloud.domain;

import com.devuni.unicloud.domain.cloud.domain.enums.Ram;
import com.devuni.unicloud.domain.cloud.domain.enums.Ssd;
import com.devuni.unicloud.global.exception.InvalidRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class DailyFee {

    @Column(name = "daily_fee", nullable = false)
    private Long fee;

    public DailyFee(Long fee) {
        validateFee(fee);
        this.fee = fee;
    }

    public static DailyFee calculateDailyFee(Ram ram, Ssd ssd) {
        return new DailyFee(ram.getDailyFee() + ssd.getDailyFee());
    }

    private void validateFee(Long fee) {
        if (fee < 0) {
            throw new InvalidRequest();
        }
    }
}
