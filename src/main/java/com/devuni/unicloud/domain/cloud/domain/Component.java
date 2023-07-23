package com.devuni.unicloud.domain.cloud.domain;

import com.devuni.unicloud.domain.cloud.domain.enums.OperatingSystem;
import com.devuni.unicloud.domain.cloud.domain.enums.Ram;
import com.devuni.unicloud.domain.cloud.domain.enums.Ssd;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Component {

    @Column(name = "operating_system", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private OperatingSystem operatingSystem;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Ram ram;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Ssd ssd;

    @Builder
    public Component(OperatingSystem operatingSystem, Ram ram, Ssd ssd) {
        this.operatingSystem = operatingSystem;
        this.ram = ram;
        this.ssd = ssd;
    }

    public static Component of(OperatingSystem operatingSystem, Ram ram, Ssd ssd) {
        return new Component(operatingSystem, ram, ssd);
    }
}
