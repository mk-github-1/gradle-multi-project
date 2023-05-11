package com.example.userinterface.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * "/css/**"などはアクセス可能
         * "/login", "logout"は全許可
         * "/rolename"はhasRoleのユーザーのみアクセス可能
         * 他のページはログイン済みユーザーのみ許可
         */
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(PathRequest.toStaticResources()
                    .atCommonLocations()).permitAll()                   
                .requestMatchers("/login").permitAll() 
                .requestMatchers("/logout").permitAll()
                // .requestMatchers("/system_administrator").hasRole("SYSTEM_ADMINISTRATOR")
                // .requestMatchers("/administrator").hasRole("ADMINISTRATOR")
                // .requestMatchers("/general").hasRole("GENERAL") 
                .anyRequest().authenticated()
            )
            /*
             * ログイン情報の処理URL
             * ログイン画面のhtml
             * ログイン成功後のリダイレクトURL
             * ログイン失敗後のリダイレクトURL
             * ログイン画面は未ログインでもアクセス可能
             */
            .formLogin(login -> login
                .loginProcessingUrl("/login") 
                .loginPage("/login") 
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
            )
            // ログアウト成功後のリダイレクトURL
            .logout(logout -> logout
                .logoutSuccessUrl("/logout")
            )
            // userDetailsService
            .userDetailsService(userDetailsService);                    

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
