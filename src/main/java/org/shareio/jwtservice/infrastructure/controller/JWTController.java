package org.shareio.jwtservice.infrastructure.controller;

import jakarta.validation.Valid;
import org.shareio.jwtservice.core.usecases.port.dto.GetTokenRequestDto;
import org.shareio.jwtservice.core.usecases.port.in.GenerateJWTTokenUseCaseInterface;
import org.shareio.jwtservice.exceptions.BadPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JWTController {

    @Autowired
    private GenerateJWTTokenUseCaseInterface generateJWTTokenUseCaseInterface;

    @PostMapping(value = "generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJWTToken(@Valid @RequestBody GetTokenRequestDto getTokenRequestDto) throws BadPasswordException {
        String token = generateJWTTokenUseCaseInterface.generateJWTToken(getTokenRequestDto).getToken();
        if (token == null) {
            return new ResponseEntity<>("", HttpStatusCode.valueOf(400));
        } else {
            return new ResponseEntity<>(token, HttpStatusCode.valueOf(200));
        }
    }
}