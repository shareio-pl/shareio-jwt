package org.shareio.jwtservice.core.usecases.port.out;

import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;

import java.util.Optional;
import java.util.UUID;

public interface GetUserSecurityDaoInterface {
    Optional<UserSecurityGetDto> getUserDto(String email);
}
