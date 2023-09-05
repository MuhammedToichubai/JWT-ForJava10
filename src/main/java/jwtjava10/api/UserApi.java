package jwtjava10.api;

import jakarta.validation.Valid;
import jwtjava10.dto.UserRequest;
import jwtjava10.dto.UserResponse;
import jwtjava10.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest userRequest){
      return ResponseEntity.ok(userService.save(userRequest));
    }

    @PutMapping("/{userId}")
    @Secured({"USER", "ADMIN"})
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                                   @RequestBody UserRequest userRequest,
                                                   Principal principal){
        return ResponseEntity.ok(userService.update(principal, userId, userRequest));
    }














}
