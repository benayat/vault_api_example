package com.vault.configuration;

import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {
    @Value("${vault.login.token}")
    private String token;
    @Bean
    public VaultConfig vaultConfig() throws VaultException {
        return new VaultConfig()
                .address("http://127.0.0.1:8200")
                .token(token)
                .build();
    }
}
