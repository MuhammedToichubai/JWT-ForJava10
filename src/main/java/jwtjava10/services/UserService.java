package jwtjava10.services;

import jwtjava10.dto.UserRequest;
import jwtjava10.dto.UserResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

/**
 * @author Mukhammed Asantegin
 */
public interface UserService {

    UserResponse save(UserRequest userRequest);


    UserResponse update(Principal principal, Long userId, UserRequest userRequest);

}
