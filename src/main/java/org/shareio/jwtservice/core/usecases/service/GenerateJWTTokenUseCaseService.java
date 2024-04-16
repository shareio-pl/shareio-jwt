package org.shareio.jwtservice.core.usecases.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.shareio.jwtservice.config.PropertySource;
import org.shareio.jwtservice.core.model.UserSecurity;
import org.shareio.jwtservice.core.model.UserSecuritySnapshot;
import org.shareio.jwtservice.core.usecases.port.dto.GetTokenRequestDto;
import org.shareio.jwtservice.core.usecases.port.dto.GetTokenResponseDto;
import org.shareio.jwtservice.core.usecases.port.dto.UserSecurityGetDto;
import org.shareio.jwtservice.core.usecases.port.in.GenerateJWTTokenUseCaseInterface;
import org.shareio.jwtservice.core.usecases.port.out.GetUserSecurityDaoInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenerateJWTTokenUseCaseService implements GenerateJWTTokenUseCaseInterface {
    PropertySource propertySource;
    GetUserSecurityDaoInterface getUserSecurityDaoInterface;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public GetTokenResponseDto generateJWTToken(GetTokenRequestDto getTokenRequestDto) {
        Optional<UserSecurityGetDto> userSecurityGetDto = getUserSecurityDaoInterface.getUserDto(getTokenRequestDto.getEmail());
        if (userSecurityGetDto.isEmpty()) {
            return null;
        }

        if (verifyPasswordMatch(getTokenRequestDto.getPassword(), userSecurityGetDto.get().password())) {
            // TODO: set last login date
            UserSecuritySnapshot userSecuritySnapshot = userSecurityGetDto
                    .map(UserSecurity::fromDto).get().toSnapshot();
            return new GetTokenResponseDto(Jwts.builder()
                    .claim("id", userSecuritySnapshot.userId().getId())
                    .claim("role", userSecuritySnapshot.accountType())
                    .setSubject("SHAREIO")
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(propertySource.getDuration(), ChronoUnit.MINUTES)))
                    .signWith(Keys.hmacShaKeyFor(propertySource.getSecret().getBytes()), SignatureAlgorithm.HS256)
                    .compact());
        }

        return null;
    }

    private Boolean verifyPasswordMatch(String providedPassword, String dbPassword) {
        return passwordEncoder.matches(providedPassword, dbPassword);
    }
}
