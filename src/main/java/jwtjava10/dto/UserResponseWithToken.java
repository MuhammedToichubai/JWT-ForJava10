package jwtjava10.dto;

import jwtjava10.models.Role;

/**
 * @author Mukhammed Asantegin
 */
public record UserResponseWithToken(
        Long id,
        String email,
        Role role,
        String token
        ) {
}
