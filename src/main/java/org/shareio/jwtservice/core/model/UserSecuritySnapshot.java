package org.shareio.jwtservice.core.model;

import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.JWTToken;
import org.shareio.jwtservice.core.model.vo.SecurityDbId;
import org.shareio.jwtservice.core.model.vo.UserId;

import java.time.LocalDateTime;

public record UserSecuritySnapshot(
        UserId userId,
        SecurityDbId securityDbId,
        String email,
        String password,
        AccountType accountType,
        LocalDateTime registrationDate,
        LocalDateTime lastLoginDate,
        JWTToken jwtToken

) {
    public UserSecuritySnapshot(UserSecurity userSecurity) {
        this(userSecurity.getUserId(), userSecurity.getSecurityDbId(), userSecurity.getEmail(), userSecurity.getPassword(),
                userSecurity.getAccountType(), userSecurity.getRegistrationDate(),
                userSecurity.getLastLoginDate(), userSecurity.getJwtToken());
    }
}
