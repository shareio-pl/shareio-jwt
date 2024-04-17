package org.shareio.jwtservice.core.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.JWTToken;
import org.shareio.jwtservice.core.model.vo.SecurityDbId;
import org.shareio.jwtservice.core.model.vo.UserId;
import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;

import java.time.LocalDateTime;

@Getter
public class UserSecurity {
    private final UserId userId;
    private final SecurityDbId securityDbId;
    private final String email;
    private final String password;
    private final AccountType accountType;
    private final LocalDateTime registrationDate;
    @Setter(AccessLevel.PUBLIC)
    private LocalDateTime lastLoginDate;
    private final JWTToken jwtToken;

    private UserSecurity(UserId userId, SecurityDbId securityDbId, String email, String password, AccountType accountType, LocalDateTime registrationDate, LocalDateTime lastLoginDate, JWTToken jwtToken) {
        this.userId = userId;
        this.securityDbId = securityDbId;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        this.jwtToken = jwtToken;
    }

    public static UserSecurity fromDto(UserSecurityGetDto userSecurityGetDto) {
        return new UserSecurity(
                userSecurityGetDto.userId(),
                userSecurityGetDto.securityDbId(),
                userSecurityGetDto.email(),
                userSecurityGetDto.password(),
                userSecurityGetDto.accountType(),
                userSecurityGetDto.registrationDate(),
                userSecurityGetDto.lastLoginDate(),
                null
        );
    }

    public UserSecuritySnapshot toSnapshot() {
        return new UserSecuritySnapshot(this);
    }
}
