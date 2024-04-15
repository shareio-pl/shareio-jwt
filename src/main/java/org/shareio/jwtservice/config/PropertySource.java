package org.shareio.jwtservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class PropertySource {
    @Value("${jwt.secret}")
    private String secret;



}
