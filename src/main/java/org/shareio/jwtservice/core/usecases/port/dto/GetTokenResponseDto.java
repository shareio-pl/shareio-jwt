package org.shareio.jwtservice.core.usecases.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetTokenResponseDto {
    private String token;
}
