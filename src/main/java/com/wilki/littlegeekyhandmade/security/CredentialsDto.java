package com.wilki.littlegeekyhandmade.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

    private String email;
    private String password;
}
