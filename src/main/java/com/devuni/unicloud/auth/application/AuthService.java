package com.devuni.unicloud.auth.application;

import com.devuni.unicloud.auth.presentation.request.Login;
import com.devuni.unicloud.auth.presentation.request.SignUp;
import com.devuni.unicloud.auth.presentation.response.LoginResponse;
import com.devuni.unicloud.domain.user.domain.Email;
import com.devuni.unicloud.domain.user.domain.Password;
import com.devuni.unicloud.domain.user.domain.User;
import com.devuni.unicloud.domain.user.domain.UserRepository;
import com.devuni.unicloud.global.exception.DuplicatedEmail;
import com.devuni.unicloud.global.exception.InvalidRequestLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public LoginResponse login(Login login) {
        User user = userRepository.findByEmailAndPassword(new Email(login.getEmail()), new Password(login.getPassword()))
                .orElseThrow(InvalidRequestLogin::new);

        return new LoginResponse(user.getId(), user.getPermission());
    }

    @Transactional
    public void signUp(SignUp signUp) {
        Optional<User> optionalUser = userRepository.findByEmail(new Email(signUp.getEmail()));
        if (optionalUser.isPresent()) {
            throw new DuplicatedEmail();
        }

        User user = User.builder()
                .name(signUp.getName())
                .email(signUp.getEmail())
                .password(signUp.getPassword())
                .build();
        userRepository.save(user);
    }
}
