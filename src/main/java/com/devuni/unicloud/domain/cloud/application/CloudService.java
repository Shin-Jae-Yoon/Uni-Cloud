package com.devuni.unicloud.domain.cloud.application;

import com.devuni.unicloud.domain.cloud.domain.Cloud;
import com.devuni.unicloud.domain.cloud.domain.CloudRepository;
import com.devuni.unicloud.domain.cloud.domain.Component;
import com.devuni.unicloud.domain.cloud.presentation.request.CloudCreateRequest;
import com.devuni.unicloud.domain.cloud.presentation.response.CloudResponse;
import com.devuni.unicloud.global.exception.NotFoundCloud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CloudService {

    private final CloudRepository cloudRepository;

    @Transactional
    public CloudResponse create(CloudCreateRequest cloudCreateRequest) {
        Cloud cloud = Cloud.builder()
                .serverName(cloudCreateRequest.getServerName())
                .component(Component.of(
                        cloudCreateRequest.getComponentRequest().getOperatingSystem(),
                        cloudCreateRequest.getComponentRequest().getRam(),
                        cloudCreateRequest.getComponentRequest().getSsd()
                ))
                .build();

        cloudRepository.save(cloud);
        return toCloudResponse(cloud);
    }

    @Transactional(readOnly = true)
    public List<CloudResponse> getList() {
        return cloudRepository.findAll().stream()
                .map(this::toCloudResponse)
                .toList();
    }

    @Transactional
    public void delete(Long cloudId) {
        Cloud cloud = cloudRepository.findById(cloudId)
                        .orElseThrow(NotFoundCloud::new);

        cloudRepository.delete(cloud);
    }

    private CloudResponse toCloudResponse(Cloud cloud) {
        return CloudResponse.builder()
                .cloudId(cloud.getId())
                .serverName(cloud.getServerName())
                .component(cloud.getComponent())
                .dailyFee(cloud.getDailyFee())
                .build();
    }
}
