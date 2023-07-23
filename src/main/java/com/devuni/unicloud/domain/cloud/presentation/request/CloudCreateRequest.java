package com.devuni.unicloud.domain.cloud.presentation.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CloudCreateRequest {

    private String serverName;
    private ComponentRequest componentRequest;

    @Builder
    public CloudCreateRequest(String serverName, ComponentRequest componentRequest) {
        this.serverName = serverName;
        this.componentRequest = componentRequest;
    }
}
