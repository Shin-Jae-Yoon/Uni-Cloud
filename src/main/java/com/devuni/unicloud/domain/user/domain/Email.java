package com.devuni.unicloud.domain.user.domain;

import com.devuni.unicloud.global.exception.InvalidRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Email {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 50;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    public Email(String email) {
        checkEmailLength(email);
        this.email = email;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    private void checkEmailLength(String email) {
        if (email.length() < LOWER_BOUND || email.length() > UPPER_BOUND) {
            throw new InvalidRequest();
        }
    }
}
