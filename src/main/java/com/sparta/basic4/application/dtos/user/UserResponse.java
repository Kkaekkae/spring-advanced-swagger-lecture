package com.sparta.basic4.application.dtos.user;

import com.sparta.basic4.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserResponse {
    String userId;
    String username;
    String email;

    public static UserResponse of(final User user) {
        return UserResponse.builder()
                .userId(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
