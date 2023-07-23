package com.devuni.unicloud.domain.instance.presentation;

import com.devuni.unicloud.domain.instance.application.InstanceService;
import com.devuni.unicloud.domain.instance.presentation.request.InstanceCreateRequest;
import com.devuni.unicloud.domain.instance.presentation.request.InstanceUpdateCloudRequest;
import com.devuni.unicloud.domain.instance.presentation.request.InstanceUpdateStatusRequest;
import com.devuni.unicloud.domain.instance.presentation.response.InstanceResponse;
import com.devuni.unicloud.global.exception.UnauthorizedUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instance")
@RequiredArgsConstructor
public class InstanceController {

    private final InstanceService instanceService;
    private final HttpSession httpSession;

    @PostMapping
    public ResponseEntity<InstanceResponse> create(@RequestBody InstanceCreateRequest instanceCreateRequest) {
        Long userId = getUserIdFromSession();

        return ResponseEntity.ok(instanceService.create(userId, instanceCreateRequest));
    }

    @GetMapping
    public ResponseEntity<List<InstanceResponse>> getList() {
        Long userId = getUserIdFromSession();

        return ResponseEntity.ok(instanceService.getList(userId));
    }

    @PutMapping("/{instanceId}/cloud")
    public ResponseEntity<InstanceResponse> updateCloud(@PathVariable(name = "instanceId") Long instanceId,
                                                        @RequestBody InstanceUpdateCloudRequest instanceUpdateCloudRequest) {
        return ResponseEntity.ok(instanceService.updateCloud(instanceId, instanceUpdateCloudRequest));
    }

    @PutMapping("/{instanceId}/status")
    public ResponseEntity<InstanceResponse> updateStatus(@PathVariable(name = "instanceId") Long instanceId,
                                                         @RequestBody InstanceUpdateStatusRequest instanceUpdateStatusRequest) {
        return ResponseEntity.ok(instanceService.updateStatus(instanceId, instanceUpdateStatusRequest));
    }

    @DeleteMapping("/{instanceId}")
    public ResponseEntity<Void> delete(@PathVariable("instanceId") Long instanceId) {
        instanceService.delete(instanceId);
        return ResponseEntity.noContent().build();
    }

    private Long getUserIdFromSession() {
        if (httpSession.getAttribute("userId") == null) {
            throw new UnauthorizedUser();
        }

        return (Long) httpSession.getAttribute("userId");
    }
}
