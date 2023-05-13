package com.example.domainservice.domain_service_impl.account;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.domainmodel.entity.account.LoginUser;
import com.example.domainservice.repository.account.LoginUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginUserRepository loginUserRepository;

    public UserDetailsServiceImpl(
    	LoginUserRepository loginUserRepository
    	) {

        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	// nullチェックのためOptional<>で受ける
    	Optional<LoginUser> optinalLoginUser = loginUserRepository.findByUserName(username);

        // nullチェック
        if (!optinalLoginUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Optional<>からLoginUserを取得
        LoginUser loginUser = optinalLoginUser.get();

        // UserDetailsを返却
        UserDetails userDetails = User.builder()
            .username(loginUser.getUsername())
            .password(loginUser.getPassword())
            // User.builderはdisabled
            .disabled(!loginUser.isEnabled())
            // User.builderは条件が反転
            .accountExpired(!loginUser.isAccountNonExpired())
            .accountLocked(!loginUser.isAccountNonLocked())
            .credentialsExpired(!loginUser.isCredentialsNonExpired())
            // User.builderはauthorities
            .authorities(loginUser.getAuthorities())
            .build();

        // optinalLoginUser.map(loginUser -> new LoginUserDetails(loginUser)); // .orElseThrow(() -> new UsernameNotFoundException("not found"));
        
        return userDetails;
    }
}
