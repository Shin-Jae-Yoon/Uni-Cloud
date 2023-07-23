package com.devuni.unicloud.domain.cloud.presentation;

import com.devuni.unicloud.domain.cloud.application.CloudService;
import com.devuni.unicloud.domain.cloud.domain.enums.OperatingSystem;
import com.devuni.unicloud.domain.cloud.domain.enums.Ram;
import com.devuni.unicloud.domain.cloud.domain.enums.Ssd;
import com.devuni.unicloud.domain.cloud.presentation.request.CloudCreateRequest;
import com.devuni.unicloud.domain.cloud.presentation.response.CloudResponse;
import com.devuni.unicloud.domain.cloud.presentation.response.ComponentValuesResponse;
import com.devuni.unicloud.domain.user.domain.enums.Permission;
import com.devuni.unicloud.global.exception.ForbiddenUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cloud")
@RequiredArgsConstructor
public class CloudController {

    private final CloudService cloudService;
    private final HttpSession httpSession;

    @PostMapping
    public ResponseEntity<CloudResponse> create(@RequestBody CloudCreateRequest cloudCreateRequest) {
        validatedUserPermission(httpSession);

        return ResponseEntity.ok(cloudService.create(cloudCreateRequest));
    }

    @GetMapping
    public ResponseEntity<List<CloudResponse>> getList() {
        return ResponseEntity.ok(cloudService.getList());
    }

    @DeleteMapping("/{cloudId}")
    public ResponseEntity<Void> delete(@PathVariable("cloudId") Long cloudId) {
        validatedUserPermission(httpSession);
        cloudService.delete(cloudId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/component")
    public ResponseEntity<ComponentValuesResponse> getComponentValues() {
        ComponentValuesResponse componentValuesResponse = ComponentValuesResponse.builder()
                .operatingSystems(Arrays.asList(OperatingSystem.values()))
                .rams(Arrays.asList(Ram.values()))
                .ssds(Arrays.asList(Ssd.values()))
                .build();

        return ResponseEntity.ok(componentValuesResponse);
    }

    private void validatedUserPermission(HttpSession httpSession) {
        Permission permission = (Permission) httpSession.getAttribute("permission");

        if (permission != Permission.MANAGER) {
            throw new ForbiddenUser();
        }
    }
}
