package com.jesus.client.client_practica_app.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authHttp) -> authHttp
                .requestMatchers(HttpMethod.GET, "/authorized").permitAll()
                .requestMatchers(HttpMethod.GET, "/catalogos/carros/**").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                        .requestMatchers(HttpMethod.POST, "/catalogos/carros/create").hasAnyAuthority("SCOPE_write", "SCOPE_read")
                        .requestMatchers(HttpMethod.DELETE, "/catalogos/carros/delete").hasAnyAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.PUT, "/catalogos/carros/update").hasAnyAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.GET, "/api/pokemones/**").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(login -> login.loginPage("/oauth2/authorization/client-app"))
                .oauth2Client(Customizer.withDefaults())
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
