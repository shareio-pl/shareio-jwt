package org.shareio.jwtservice.core.usecases.port.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTokenRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
