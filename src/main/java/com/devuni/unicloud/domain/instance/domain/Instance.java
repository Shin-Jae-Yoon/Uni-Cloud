package com.devuni.unicloud.domain.instance.domain;

import com.devuni.unicloud.domain.cloud.domain.Cloud;
import com.devuni.unicloud.domain.instance.domain.enums.InstanceStatus;
import com.devuni.unicloud.domain.user.domain.User;
import com.devuni.unicloud.global.domain.BaseTime;
import com.devuni.unicloud.global.exception.InvalidInstanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "instances")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Instance extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instance_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cloud_id")
    private Cloud cloud;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private InstanceStatus status;

    public Instance(User user, Cloud cloud) {
        this.user = user;
        this.cloud = cloud;
        this.status = InstanceStatus.READY;
    }

    public void updateCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public void updateStatus(InstanceStatus status) {
        validateInstanceStatus();
        this.status = status;
    }

    private void validateInstanceStatus() {
        if (this.status.equals(InstanceStatus.TERMINATED)) {
            throw new InvalidInstanceStatus();
        }
    }
}
