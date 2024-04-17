package org.shareio.jwtservice.core.model;

import lombok.Getter;
import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.JWTToken;
import org.shareio.jwtservice.core.model.vo.UserId;
import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;

@Getter
public class UserSecurity {
    private UserId userId;
    private String email;
    private String password;
    private AccountType accountType;
    private JWTToken jwtToken;

    private UserSecurity(UserId userId, String email, String password, AccountType accountType, JWTToken jwtToken) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.jwtToken = jwtToken;
    }

    public static UserSecurity fromDto(UserSecurityGetDto userSecurityGetDto) {
        return new UserSecurity(
                userSecurityGetDto.userId(),
                userSecurityGetDto.email(),
                userSecurityGetDto.password(),
                userSecurityGetDto.accountType(),
                null
        );
    }

    public UserSecuritySnapshot toSnapshot() {
        return new UserSecuritySnapshot(this);
    }
}
