package top.haidong556.oauth;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity

@SpringBootApplication
@EnableDiscoveryClient
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }

}
