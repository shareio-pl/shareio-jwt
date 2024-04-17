package org.shareio.jwtservice.core.usecases.port.out;

import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;

import java.util.Optional;

public interface GetUserSecurityDaoInterface {
    Optional<UserSecurityGetDto> getUserDto(String email);
}
