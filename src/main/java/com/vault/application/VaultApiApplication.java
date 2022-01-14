package com.vault.application;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages= "com.vault.configuration")
public class VaultApiApplication implements CommandLineRunner {

    @Autowired
    VaultConfig vaultConfig;

    public static void main(String[] args)  {
        SpringApplication.run(VaultApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final Vault vault = new Vault(vaultConfig);
        final Map<String, Object> secrets = new HashMap<>();
        secrets.put("value", "world");
        secrets.put("other_value", "You can store multiple name/value pairs under a single key");
        final LogicalResponse writeResponse = vault.logical()
                .write("secret/hello", secrets);

        final String value = vault.logical()
                .read("secret/hello")
                .getData().get("value");
        System.out.println(value);

    }


}
