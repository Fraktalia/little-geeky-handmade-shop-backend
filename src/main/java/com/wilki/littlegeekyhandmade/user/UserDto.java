package com.wilki.littlegeekyhandmade.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    @Pattern(regexp = "\\b[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\..+\\b", message = "Email address must be valid")
    private String email;
    private String password;
}