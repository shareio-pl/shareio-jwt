package org.shareio.jwtservice.core.usecases.port.in;

import org.shareio.jwtservice.core.usecases.port.dto.GetTokenRequestDto;
import org.shareio.jwtservice.core.usecases.port.dto.GetTokenResponseDto;
import org.shareio.jwtservice.exceptions.BadPasswordException;

public interface GenerateJWTTokenUseCaseInterface {
    GetTokenResponseDto generateJWTToken(GetTokenRequestDto getTokenRequestDto) throws BadPasswordException;
}
