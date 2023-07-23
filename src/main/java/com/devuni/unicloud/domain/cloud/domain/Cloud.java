package com.devuni.unicloud.domain.cloud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cloud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cloud_id")
    private Long id;

    @Column(name = "server_name")
    private String serverName;

    @Embedded
    private Component component;

    @Embedded
    private DailyFee dailyFee;

    @Builder
    public Cloud(String serverName, Component component) {
        this.serverName = serverName;
        this.component = component;
        this.dailyFee = DailyFee.calculateDailyFee(component.getRam(), component.getSsd());
    }
}
