package com.devuni.unicloud.domain.instance.presentation.response;

import com.devuni.unicloud.domain.cloud.domain.Cloud;
import com.devuni.unicloud.domain.instance.domain.enums.InstanceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InstanceResponse {

    private Long id;
    private Cloud cloud;
    private InstanceStatus status;

    @Builder
    public InstanceResponse(Long id, Cloud cloud, InstanceStatus status) {
        this.id = id;
        this.cloud = cloud;
        this.status = status;
    }
}
