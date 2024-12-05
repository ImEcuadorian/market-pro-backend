package io.github.imecuadorian.product.configuration.auth;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/products/**").permitAll()
//                                .requestMatchers(HttpMethod.PUT, "/products/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE, "/products/**").permitAll()
//                )
//                .sessionManagement(sessionManagement -> {
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                }).oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer.jwt(Customizer.withDefaults()));
//
//        return httpSecurity.build();
//    }
}
