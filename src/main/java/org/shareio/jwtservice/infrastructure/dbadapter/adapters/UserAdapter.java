package org.shareio.jwtservice.infrastructure.dbadapter.adapters;

import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;
import org.shareio.jwtservice.core.usecases.port.out.GetUserSecurityDaoInterface;
import org.shareio.jwtservice.infrastructure.dbadapter.entities.UserEntity;
import org.shareio.jwtservice.infrastructure.dbadapter.mappers.UserDatabaseMapper;
import org.shareio.jwtservice.infrastructure.dbadapter.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserAdapter implements GetUserSecurityDaoInterface {
    final UserRepository userRepository;

    public UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<UserSecurityGetDto> getUserDto(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new NoSuchElementException();
        }
        return userEntity.map(UserDatabaseMapper::toDto);
    }
}

