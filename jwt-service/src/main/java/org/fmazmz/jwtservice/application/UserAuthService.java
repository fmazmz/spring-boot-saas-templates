package org.fmazmz.jwtservice.application;

import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.fmazmz.jwtservice.security.JwtService;
import org.fmazmz.jwtservice.model.User;
import org.fmazmz.jwtservice.model.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Transactional
    public void registerUser(@Size(min = 5) String username, @Size(min = 8) String password) {
        if (userRepository.existsByUsername(username)) throw new IllegalArgumentException("Duplicate username");

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof User user)) {
            throw new IllegalStateException("Unexpected principal type");
        }

        return jwtService.generateToken(user);
    }
}
