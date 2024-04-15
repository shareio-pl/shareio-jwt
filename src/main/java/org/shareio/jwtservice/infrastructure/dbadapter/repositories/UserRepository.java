package org.shareio.jwtservice.infrastructure.dbadapter.repositories;

import org.shareio.jwtservice.infrastructure.dbadapter.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

}
