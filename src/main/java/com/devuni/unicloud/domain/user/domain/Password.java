package com.devuni.unicloud.domain.user.domain;

import com.devuni.unicloud.global.exception.InvalidRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Password {

    private static final int LOWER_BOUND = 8;
    private static final int UPPER_BOUND = 20;

    @Column(length = 20, nullable = false)
    private String password;

    public Password(String password) {
        validatePasswordLength(password);
        this.password = password;
    }

    public static Password of(String password) {
        return new Password(password);
    }

    private void validatePasswordLength(String password) {
        if (password.length() < LOWER_BOUND || password.length() > UPPER_BOUND) {
            throw new InvalidRequest();
        }
    }
}
