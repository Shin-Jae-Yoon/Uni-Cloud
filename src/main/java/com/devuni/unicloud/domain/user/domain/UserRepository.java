package com.devuni.unicloud.domain.user.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(Email email);

    Optional<User> findByEmailAndPassword(Email email, Password password);
}
