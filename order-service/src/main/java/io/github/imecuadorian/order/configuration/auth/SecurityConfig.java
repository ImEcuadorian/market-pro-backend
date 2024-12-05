package io.github.imecuadorian.order.configuration.auth;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests.requestMatchers(HttpMethod.GET, "/orders/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .sessionManagement(sessionManagement -> {
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                }).oauth2ResourceServer(oauth2ResourceServer ->
//                {
//                    oauth2ResourceServer.jwt(Customizer.withDefaults());
//                });
//
//        return httpSecurity.build();
//    }
}
