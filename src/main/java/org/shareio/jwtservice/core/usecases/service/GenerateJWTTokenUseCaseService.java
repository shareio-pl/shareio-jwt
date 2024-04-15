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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static final BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();

    @Override
    public GetTokenResponseDto generateJWTToken(GetTokenRequestDto getTokenRequestDto) {
        String secret = propertySource.getSecret();
        Optional<UserSecurityGetDto> userSecurityGetDto = getUserSecurityDaoInterface.getUserDto(getTokenRequestDto.getEmail());
        if(userSecurityGetDto.isPresent()){
            UserSecurity userSecurity = userSecurityGetDto.map(UserSecurity::fromDto).get();
            if(verifyPasswordMatch(getTokenRequestDto.getPassword(), userSecurityGetDto.get().password())){
                UserSecuritySnapshot userSecuritySnapshot = userSecurity.toSnapshot();
                return new GetTokenResponseDto(Jwts.builder()
                        .claim("id",userSecuritySnapshot.userId().getId())
                        .claim("role","user")
                        .setSubject("SHAREIO")
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
                        .signWith(Keys.hmacShaKeyFor(secret.getBytes()),SignatureAlgorithm.HS256).compact());
            }
        }
        return null;
    }

    private Boolean verifyPasswordMatch(String providedPassword, String dbPassword) {
        return passwordEncorder.matches(providedPassword, dbPassword);
    }
}
