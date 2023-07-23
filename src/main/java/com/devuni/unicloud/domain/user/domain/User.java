package com.devuni.unicloud.domain.user.domain;

import com.devuni.unicloud.domain.user.domain.enums.Permission;
import com.devuni.unicloud.global.domain.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class User extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Builder
    public User(String name, String email, String password) {
        this.name = Name.of(name);
        this.email = Email.of(email);
        this.password = Password.of(password);
        this.permission = Permission.NORMAL;
    }
}
