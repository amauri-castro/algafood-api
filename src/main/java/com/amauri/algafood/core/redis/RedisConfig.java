package com.amauri.algafood.core.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

import java.util.HashMap;

@Configuration
public class RedisConfig {

    @Bean
    @Profile("dev")
    public SessionRepository<?> sessionRepository() {
        return new MapSessionRepository(new HashMap<>());
    }
}
