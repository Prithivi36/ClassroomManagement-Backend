package com.Classroom.Classroom.Security;

import com.Classroom.Classroom.Security.JWTS.JwtsAuthenticationEntryPoint;
import com.Classroom.Classroom.Security.JWTS.JwtsAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfigurstion {
    private UserDetailsService userDetailsService;

    private JwtsAuthenticationEntryPoint jwtsAuthenticationEntryPoint;
    private JwtsAuthenticationFilter jwtsAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorizer)->{
//                    authorizer.requestMatchers(HttpMethod.GET,"/**").hasRole("ADMIN");
                    authorizer.requestMatchers(HttpMethod.POST,"/auth/**").permitAll();
                    authorizer.requestMatchers(HttpMethod.POST,"/student/new").permitAll();
                    authorizer.requestMatchers(HttpMethod.GET,"/download/**").permitAll();
                    authorizer.requestMatchers(HttpMethod.GET,"/files/**").permitAll();
                    authorizer.anyRequest().authenticated();

                }).httpBasic(Customizer.withDefaults());

        httpSecurity.exceptionHandling((exception->exception.authenticationEntryPoint(jwtsAuthenticationEntryPoint)));

        httpSecurity.addFilterBefore(jwtsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
