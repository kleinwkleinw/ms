package de.wk.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { RedisConfig.class })
public class UserServiceConfig {
}
