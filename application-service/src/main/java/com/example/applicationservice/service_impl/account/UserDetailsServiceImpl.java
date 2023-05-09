package com.example.applicationservice.service_impl.account;

import java.util.*;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

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
        return User.builder()
                .username(loginUser.getUsername())
                .password(loginUser.getPassword())
                .authorities(loginUser.getAuthorities())
                .accountExpired(!loginUser.isAccountNonExpired())
                .accountLocked(!loginUser.isAccountNonLocked())
                .credentialsExpired(!loginUser.isCredentialsNonExpired())
                .disabled(!loginUser.isEnabled())
                .build();
    }
}
