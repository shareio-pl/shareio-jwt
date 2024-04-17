package org.shareio.jwtservice.core.usecases.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTokenRequestDto {
    private String email;
    private String password;
}
