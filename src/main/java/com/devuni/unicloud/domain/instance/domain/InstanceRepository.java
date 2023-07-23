package com.devuni.unicloud.domain.instance.domain;

import com.devuni.unicloud.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstanceRepository extends JpaRepository<Instance, Long> {

    List<Instance> findInstanceByUser(User user);
}
