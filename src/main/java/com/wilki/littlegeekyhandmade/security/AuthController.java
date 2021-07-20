package com.wilki.littlegeekyhandmade.security;

import com.wilki.littlegeekyhandmade.user.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserSecurityService userSecurityService;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void register(@RequestBody UserDto userDto) {
        userSecurityService.register(userDto);
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public JwtResponse login(@RequestBody CredentialsDto credentialsDto) {
        Authentication authentication = userSecurityService.login(credentialsDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtResponse(jwtUtils.generateJwtToken(authentication));
    }
}
