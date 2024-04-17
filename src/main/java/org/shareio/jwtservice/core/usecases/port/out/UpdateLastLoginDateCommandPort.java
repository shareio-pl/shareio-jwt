package org.shareio.jwtservice.core.usecases.port.out;

import java.time.LocalDateTime;

public interface UpdateLastLoginDateCommandPort {
    void updateUserSecurity(Long dbId, LocalDateTime lastLoginDate);
}
