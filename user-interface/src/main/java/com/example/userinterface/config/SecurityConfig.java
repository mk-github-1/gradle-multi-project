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
    	http
    		.csrf(c -> c.ignoringRequestMatchers(
    				new AntPathRequestMatcher("/login", HttpMethod.GET.toString())
    		))
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(PathRequest.toStaticResources()
                    .atCommonLocations()).permitAll()                   // "/css/**"などはアクセス可能 */
                .requestMatchers("/login").permitAll()	    // ログインページは全許可
                .requestMatchers("/logout").permitAll()	    // ログインページは全許可
                // .requestMatchers("/general").hasRole("GENERAL")      // "/general"はROLE_GENERALのみアクセス可能
                // .requestMatchers("/admin").hasRole("ADMIN")          // "/admin"はROLE_ADMINのみアクセス可能
                .anyRequest().authenticated()			                // 他のページはログイン済みユーザーのみ許可
            )
            .formLogin(login -> login
                .loginProcessingUrl("/login")        // ログイン情報の処理URL
                .loginPage("/login")                          // ログイン画面のhtml
                .defaultSuccessUrl("/")               // ログイン成功後のリダイレクトURL
                .failureUrl("/login?error")    // ログイン失敗後のリダイレクトURL
                .permitAll()                                            // ログイン画面は未ログインでもアクセス可能
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/logout")           // ログアウト成功後のリダイレクトURL
            )
            .userDetailsService(userDetailsService);                    // userDetailsService

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
