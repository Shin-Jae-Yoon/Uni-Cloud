package com.devuni.unicloud.domain.instance.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InstanceCreateRequest {

    private Long cloudId;

    public InstanceCreateRequest(Long cloudId) {
        this.cloudId = cloudId;
    }
}
