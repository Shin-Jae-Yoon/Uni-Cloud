package com.devuni.unicloud.domain.cloud.presentation.request;

import com.devuni.unicloud.domain.cloud.domain.enums.OperatingSystem;
import com.devuni.unicloud.domain.cloud.domain.enums.Ram;
import com.devuni.unicloud.domain.cloud.domain.enums.Ssd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComponentRequest {

    private OperatingSystem operatingSystem;
    private Ram ram;
    private Ssd ssd;

    @Builder
    public ComponentRequest(OperatingSystem operatingSystem, Ram ram, Ssd ssd) {
        this.operatingSystem = operatingSystem;
        this.ram = ram;
        this.ssd = ssd;
    }
}
