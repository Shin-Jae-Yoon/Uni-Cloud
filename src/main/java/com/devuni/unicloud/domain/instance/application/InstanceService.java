package com.devuni.unicloud.domain.instance.application;

import com.devuni.unicloud.domain.cloud.domain.Cloud;
import com.devuni.unicloud.domain.cloud.domain.CloudRepository;
import com.devuni.unicloud.domain.instance.domain.Instance;
import com.devuni.unicloud.domain.instance.domain.InstanceRepository;
import com.devuni.unicloud.domain.instance.presentation.request.InstanceCreateRequest;
import com.devuni.unicloud.domain.instance.presentation.request.InstanceUpdateCloudRequest;
import com.devuni.unicloud.domain.instance.presentation.request.InstanceUpdateStatusRequest;
import com.devuni.unicloud.domain.instance.presentation.response.InstanceResponse;
import com.devuni.unicloud.domain.user.domain.User;
import com.devuni.unicloud.domain.user.domain.UserRepository;
import com.devuni.unicloud.global.exception.NotFoundCloud;
import com.devuni.unicloud.global.exception.NotFoundInstance;
import com.devuni.unicloud.global.exception.NotFoundUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstanceService {

    private final InstanceRepository instanceRepository;
    private final CloudRepository cloudRepository;
    private final UserRepository userRepository;

    @Transactional
    public InstanceResponse create(Long userId, InstanceCreateRequest instanceCreateRequest) {
        Instance instance = new Instance(validateUser(userId), validateCloud(instanceCreateRequest.getCloudId()));

        instanceRepository.save(instance);
        return toInstanceResponse(instance);
    }

    @Transactional(readOnly = true)
    public List<InstanceResponse> getList(Long userId) {
        User user = validateUser(userId);

        return instanceRepository.findInstanceByUser(user).stream()
                .map(this::toInstanceResponse)
                .toList();
    }

    @Transactional
    public InstanceResponse updateCloud(Long instanceId, InstanceUpdateCloudRequest instanceUpdateCloudRequest) {
        Instance instance = validateInstance(instanceId);
        Cloud cloud = validateCloud(instanceUpdateCloudRequest.getCloudId());

        instance.updateCloud(cloud);
        return toInstanceResponse(instance);
    }

    @Transactional
    public InstanceResponse updateStatus(Long instanceId, InstanceUpdateStatusRequest instanceUpdateCloudRequest) {
        Instance instance = validateInstance(instanceId);

        instance.updateStatus(instanceUpdateCloudRequest.getStatus());
        return toInstanceResponse(instance);
    }

    @Transactional
    public void delete(Long cloudId) {
        Cloud cloud = cloudRepository.findById(cloudId)
                .orElseThrow(NotFoundCloud::new);

        cloudRepository.delete(cloud);
    }

    private Instance validateInstance(Long instanceId) {
        return instanceRepository.findById(instanceId)
                .orElseThrow(NotFoundInstance::new);
    }

    private Cloud validateCloud(Long cloudId) {
        return cloudRepository.findById(cloudId)
                .orElseThrow(NotFoundCloud::new);
    }

    private User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUser::new);
    }

    private InstanceResponse toInstanceResponse(Instance instance) {
        return InstanceResponse.builder()
                .id(instance.getId())
                .cloud(instance.getCloud())
                .status(instance.getStatus())
                .build();
    }
}
