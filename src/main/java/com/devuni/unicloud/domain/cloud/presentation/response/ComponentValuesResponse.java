package com.devuni.unicloud.domain.cloud.presentation.response;

import com.devuni.unicloud.domain.cloud.domain.enums.OperatingSystem;
import com.devuni.unicloud.domain.cloud.domain.enums.Ram;
import com.devuni.unicloud.domain.cloud.domain.enums.Ssd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ComponentValuesResponse {

    private List<OperatingSystem> operatingSystems;
    private List<Ram> rams;
    private List<Ssd> ssds;

    @Builder
    public ComponentValuesResponse(List<OperatingSystem> operatingSystems, List<Ram> rams, List<Ssd> ssds) {
        this.operatingSystems = operatingSystems;
        this.rams = rams;
        this.ssds = ssds;
    }
}
