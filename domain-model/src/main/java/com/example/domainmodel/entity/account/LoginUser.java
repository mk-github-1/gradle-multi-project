package com.example.domainmodel.entity.account;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

/**
 * LoginUser
 *
 */
@Entity
@Table(name="login_user")
public class LoginUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	public LoginUser(
		String username,
		String password,
		Collection<? extends GrantedAuthority> authorities,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Date created_at,
        Date updated_at,
        long timestamp
		) {

		this.username = username;
        this.password = password;
        // this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.timestamp = timestamp;
	}

	@Id
	@Column(length = 256)
	private String username;

	@Column(length = 256, nullable = false)
	private String password;

    // private Collection<? extends GrantedAuthority> authorities;

	@Column(nullable = false)
    private boolean accountNonExpired;

	@Column(nullable = false)
    private boolean accountNonLocked;

	@Column(nullable = false)
    private boolean credentialsNonExpired;

	@Column(nullable = false)
    private boolean enabled;

	@Column(nullable = false)
	private Date created_at;

	@Column(nullable = false)
	private Date updated_at;

	@Version
	@Column(nullable = false)
	private long timestamp;

    @OneToMany(mappedBy = "loginUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<LoginUserRole> loginUserRoles = new HashSet<>();
    
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        for (LoginUserRole loginUserRole : loginUserRoles) {
            authorities.add(new SimpleGrantedAuthority(loginUserRole.getRoleId()));
        }
        
        return authorities;
    }
        
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
