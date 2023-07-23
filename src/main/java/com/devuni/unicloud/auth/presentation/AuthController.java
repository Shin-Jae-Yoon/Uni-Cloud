package com.devuni.unicloud.auth.presentation;

import com.devuni.unicloud.auth.application.AuthService;
import com.devuni.unicloud.auth.presentation.request.Login;
import com.devuni.unicloud.auth.presentation.request.SignUp;
import com.devuni.unicloud.auth.presentation.response.LoginResponse;
import com.devuni.unicloud.domain.user.domain.enums.Permission;
import com.devuni.unicloud.global.exception.ForbiddenUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final HttpSession httpSession;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid Login login) {
        LoginResponse response = authService.login(login);

        httpSession.setAttribute("userId", response.getUserId());
        httpSession.setAttribute("permission", response.getPermission());
        httpSession.setMaxInactiveInterval(1800);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUp signUp) {
        authService.signUp(signUp);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        httpSession.invalidate();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/permission")
    public ResponseEntity<Void> permission() {
        validatedUserPermission(httpSession);

        return ResponseEntity.ok().build();
    }

    private void validatedUserPermission(HttpSession httpSession) {
        Permission permission = (Permission) httpSession.getAttribute("permission");

        if (permission != Permission.MANAGER) {
            throw new ForbiddenUser();
        }
    }
}
