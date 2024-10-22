package com.example.chercheur;

import com.example.chercheur.Configuration.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaConfig.class)
@SpringBootApplication
public class ChercheurApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChercheurApplication.class, args);
    }

}
