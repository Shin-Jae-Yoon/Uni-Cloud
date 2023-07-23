package com.devuni.unicloud.domain.cloud.presentation.response;

import com.devuni.unicloud.domain.cloud.domain.Component;
import com.devuni.unicloud.domain.cloud.domain.DailyFee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CloudResponse {

    private Long cloudId;
    private String serverName;
    private Component component;
    private DailyFee dailyFee;

    @Builder
    public CloudResponse(Long cloudId, String serverName, Component component, DailyFee dailyFee) {
        this.cloudId = cloudId;
        this.serverName = serverName;
        this.component = component;
        this.dailyFee = dailyFee;
    }
}
