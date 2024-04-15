package org.shareio.jwtservice.core.model;

import org.shareio.jwtservice.core.model.vo.JWTToken;
import org.shareio.jwtservice.core.model.vo.Role;
import org.shareio.jwtservice.core.model.vo.UserId;

import java.util.List;

public record UserSecuritySnapshot (
        UserId userId,
        String email,
        String password,
        Role role,
        JWTToken jwtToken

){
    public UserSecuritySnapshot(UserSecurity userSecurity) {
        this(userSecurity.getUserId(), userSecurity.getEmail(), userSecurity.getPassword(), userSecurity.getRole(), userSecurity.getJwtToken());
    }
}
