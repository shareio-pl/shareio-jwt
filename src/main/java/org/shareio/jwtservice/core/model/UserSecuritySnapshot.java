package org.shareio.jwtservice.core.model;

import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.JWTToken;
import org.shareio.jwtservice.core.model.vo.UserId;

public record UserSecuritySnapshot(
        UserId userId,
        String email,
        String password,
        AccountType accountType,
        JWTToken jwtToken

) {
    public UserSecuritySnapshot(UserSecurity userSecurity) {
        this(userSecurity.getUserId(), userSecurity.getEmail(), userSecurity.getPassword(),
                userSecurity.getAccountType(), userSecurity.getJwtToken());
    }
}
