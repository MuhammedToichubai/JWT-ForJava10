package jwtjava10.dto;

import jwtjava10.models.Role;
import jwtjava10.models.User;

/**
 * @author Mukhammed Asantegin
 */
public record UserResponse(
        Long id,
        String fullName,
        String email,
        Role role
) {
    public static UserResponse build(User user){
      return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
