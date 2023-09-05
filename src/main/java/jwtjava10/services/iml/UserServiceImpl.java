package jwtjava10.services.iml;

import jakarta.transaction.Transactional;
import jwtjava10.dto.UserRequest;
import jwtjava10.dto.UserResponse;
import jwtjava10.models.Role;
import jwtjava10.models.User;
import jwtjava10.repositories.UserRepository;
import jwtjava10.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.security.Principal;

/**
 * @author Mukhammed Asantegin
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = userRequest.build();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserResponse.build(user);
    }

    @Override
    @Transactional
    public UserResponse update(Principal principal, Long userId, UserRequest userRequest) {
        User userForResponse = new User();
        String email = principal.getName();
        User authUser = userRepository.getUserByEmail(email).orElseThrow(() ->
                new RuntimeException("User with email: " + email + " not found!"));
        if (authUser.getRole().equals(Role.ADMIN)){
           userForResponse = update(userRequest, userId);
        }
        else if (authUser.getRole().equals(Role.USER)){
            if (authUser.getId().equals(userId)){
              userForResponse =  update(userRequest, userId);
            }else {
                throw new RuntimeException("Sen bashka userdin info. ozgortkonu araket kylyp jatasyn!!!");
            }
        }
        return new UserResponse(
                userForResponse.getId(),
                userForResponse.getFullName(),
                userForResponse.getEmail(),
                userForResponse.getRole());
    }

    private User update(UserRequest userRequest, Long userId){
        User newUser = userRequest.build();

        User parUser = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User with id: " + userId + " not found!"));
        parUser.setFullName(newUser.getFullName());
        parUser.setEmail(newUser.getEmail());

        return newUser;
    }
}
