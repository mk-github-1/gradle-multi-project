package com.example.userinterface.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    public SecurityConfig() {

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
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
                /*
                // デバッグ専用、例外が発生するので注意 
                .failureHandler((request, response, exception) -> {
                    exception.printStackTrace();
                })
                 */
            )
            // ログアウト成功後のリダイレクトURL
            .logout(logout -> logout
                .logoutSuccessUrl("/")
            )
            /*
             * "/css/**"などはアクセス可能
             * "/login", "logout"は全許可
             * "/rolename"はhasRoleのユーザーのみアクセス可能
             * 他のページはログイン済みユーザーのみ許可
             */
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                // .requestMatchers("/system_administrator").hasRole("SYSTEM_ADMINISTRATOR")
                // .requestMatchers("/administrator").hasRole("ADMINISTRATOR")
                // .requestMatchers("/general").hasRole("GENERAL") 
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
