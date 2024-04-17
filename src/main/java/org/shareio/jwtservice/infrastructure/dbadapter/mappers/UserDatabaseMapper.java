package org.shareio.jwtservice.infrastructure.dbadapter.mappers;

import org.shareio.jwtservice.core.model.vo.AccountType;
import org.shareio.jwtservice.core.model.vo.UserId;
import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;
import org.shareio.jwtservice.infrastructure.dbadapter.entities.UserEntity;

public class UserDatabaseMapper {
    public static UserSecurityGetDto toDto(final UserEntity userEntity) {
        return new UserSecurityGetDto(
                new UserId(userEntity.getUserId()),
                userEntity.getEmail(),
                userEntity.getSecurity().getPwHash(),
                AccountType.valueOf(userEntity.getSecurity().getAccountType())
        );
    }
}
