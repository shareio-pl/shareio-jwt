package org.shareio.jwtservice.core.usecases.port.dto;

import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.SecurityDbId;
import org.shareio.jwtservice.core.model.vo.UserId;

import java.time.LocalDateTime;

public record UserSecurityGetDto(
        UserId userId,
        SecurityDbId securityDbId,
        String email,
        String password,
        AccountType accountType,
        LocalDateTime registrationDate,
        LocalDateTime lastLoginDate
) {
}
