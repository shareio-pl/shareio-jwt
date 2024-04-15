package org.shareio.jwtservice.core.usecases.port.in;

import org.shareio.jwtservice.core.usecases.port.dto.GetTokenRequestDto;
import org.shareio.jwtservice.core.usecases.port.dto.GetTokenResponseDto;

public interface GenerateJWTTokenUseCaseInterface {
    public GetTokenResponseDto generateJWTToken(GetTokenRequestDto getTokenRequestDto);

}
