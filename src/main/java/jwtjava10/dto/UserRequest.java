package jwtjava10.dto;

import jwtjava10.models.Role;
import jwtjava10.models.User;

/**
 * @author Mukhammed Asantegin
 */
public record UserRequest(
        String fullName,
        String email,
        String password,
        Role role
) {

    public User build(){
      return User.builder()
                .fullName(this.fullName)
                .email(this.email)
                .password(this.password)
                 .role(this.role)
                .build();
    }
}
