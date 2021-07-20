package com.wilki.littlegeekyhandmade.security;

import com.wilki.littlegeekyhandmade.user.User;
import com.wilki.littlegeekyhandmade.user.UserDto;
import com.wilki.littlegeekyhandmade.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(UserDto userDto){
        User user = new User(userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword()));
        failIfUserAlreadyRegistered(userDto.getEmail());
        userRepository.save(user);
    }

    public Authentication login(CredentialsDto credentialsDto){
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentialsDto.getEmail(), credentialsDto.getPassword()));
    }

    private void failIfUserAlreadyRegistered(String email) {
        Optional<User> maybeUser = userRepository.findByEmail(email);
        if (maybeUser.isPresent()){
            throw new ValidationException("User already exists: " + maybeUser.get().getEmail());
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }
}
