package ru.mirea.newrav1k.authservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.newrav1k.authservice.security.utils.KeyUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Configuration
public class RSAKeyConfiguration {

    @Value("${auth-service.jwt.private-key-path}")
    private String privateKeyPath;

    @Value("${auth-service.jwt.public-key-path}")
    private String publicKeyPath;

    @Bean
    public RSAPrivateKey rsaPrivateKey() {
        try {
            RSAPrivateKey rsaPrivateKey = KeyUtils.loadPrivateKey(this.privateKeyPath);
            log.info("RSA Private Key loaded successfully");

            return rsaPrivateKey;
        } catch (Exception exception) {
            throw new RuntimeException("RSA Private Key could not be loaded", exception);
        }
    }

    @Bean
    public RSAPublicKey rsaPublicKey() {
        try {
            RSAPublicKey rsaPublicKey = KeyUtils.loadPublicKey(this.publicKeyPath);
            log.info("RSA Public Key loaded successfully");

            return rsaPublicKey;
        } catch (Exception exception) {
            throw new RuntimeException("RSA Public Key could not be loaded", exception);
        }
    }

}