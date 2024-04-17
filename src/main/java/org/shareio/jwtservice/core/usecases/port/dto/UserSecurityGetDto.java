package org.shareio.jwtservice.core.usecases.port.dto;

import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.UserId;

public record UserSecurityGetDto(
        UserId userId,
        String email,
        String password,
        AccountType accountType
) {
}
