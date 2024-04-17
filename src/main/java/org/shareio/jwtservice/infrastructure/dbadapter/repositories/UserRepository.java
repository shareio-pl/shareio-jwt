package org.shareio.jwtservice.infrastructure.dbadapter.repositories;

import jakarta.transaction.Transactional;
import org.shareio.jwtservice.infrastructure.dbadapter.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update SecurityEntity s set s.lastLoginDate = :lastLoginDate where s.dbId = :dbId")
    void updateLastLoginDate(Long dbId, LocalDateTime lastLoginDate);
}
