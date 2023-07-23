package com.devuni.unicloud.domain.cloud.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CloudRepository extends JpaRepository<Cloud, Long> {

    Optional<Cloud> findCloudById(Long id);
}
