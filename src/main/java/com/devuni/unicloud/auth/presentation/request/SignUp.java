package com.devuni.unicloud.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUp {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,20}", message = "비밀번호는 최소 8자 ~ 최대 20자, 영문과 숫자를 혼합해주세요.")
    private String password;

    @Builder
    public SignUp(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
