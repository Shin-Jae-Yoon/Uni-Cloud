package com.devuni.unicloud.domain.instance.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InstanceUpdateCloudRequest {

    private Long cloudId;

    public InstanceUpdateCloudRequest(Long cloudId) {
        this.cloudId = cloudId;
    }
}
