package com.devuni.unicloud.domain.user.domain;

import com.devuni.unicloud.global.exception.InvalidRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Name {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 20;

    @Column(length = 20, nullable = false)
    private String name;

    public Name(String name) {
        checkNameLength(name);
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private void checkNameLength(String name) {
        if (name.length() < LOWER_BOUND || name.length() > UPPER_BOUND) {
            throw new InvalidRequest();
        }
    }
}
