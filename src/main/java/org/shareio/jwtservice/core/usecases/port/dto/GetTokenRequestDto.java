package org.shareio.jwtservice.core.usecases.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetTokenRequestDto {
    private String email;
    private String password;
}
