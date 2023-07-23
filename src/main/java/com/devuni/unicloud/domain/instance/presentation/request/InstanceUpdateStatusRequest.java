package com.devuni.unicloud.domain.instance.presentation.request;

import com.devuni.unicloud.domain.instance.domain.enums.InstanceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InstanceUpdateStatusRequest {

    private InstanceStatus status;

    public InstanceUpdateStatusRequest(InstanceStatus status) {
        this.status = status;
    }
}
