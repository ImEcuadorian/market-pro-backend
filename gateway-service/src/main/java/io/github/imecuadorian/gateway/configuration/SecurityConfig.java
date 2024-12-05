package io.github.imecuadorian.gateway.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/orders/**").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/authorized").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2Client(Customizer.withDefaults())
//                .oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer.jwt(Customizer.withDefaults()));
//
//        return httpSecurity.build();
//    }
}
