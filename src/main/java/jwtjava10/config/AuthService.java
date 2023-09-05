package jwtjava10.config;

import jwtjava10.dto.AuthRequest;
import jwtjava10.dto.UserResponseWithToken;
import jwtjava10.models.User;
import jwtjava10.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mukhammed Asantegin
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserResponseWithToken login(AuthRequest authRequest) {
        User user = userRepository.getUserByEmail(authRequest.email()).orElseThrow(() ->
                new RuntimeException(String.format("User with email: %s not found!", authRequest.email())));

        String password = authRequest.password();
        String dbEncodePassword = user.getPassword();

       if (!passwordEncoder.matches(password, dbEncodePassword)){
           throw new RuntimeException("Invalid password");
       }
        String token = jwtService.createToken(user);

        return new UserResponseWithToken(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                token
                );
    }
}
