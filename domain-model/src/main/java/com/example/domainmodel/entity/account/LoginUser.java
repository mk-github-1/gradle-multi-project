package com.example.domainmodel.entity.account;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

/**
 * LoginUser: ログインユーザー
 *
 */
@Entity
@Table(name="m_login_user")
public class LoginUser implements UserDetails {
	private static final long serialVersionUID = 1L;

    public LoginUser() {

    }

	public LoginUser(
		String username,
		String password,
        boolean enabled,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        long sortOrder,
        boolean isDeleted,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        long timestamp
		) {

		this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.sortOrder = sortOrder;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.timestamp = timestamp;
	}

	@Id
	@Column(length = 256)
	private String username;

	@Column(length = 256, nullable = false)
	private String password;
    
    // アカウントが有効かどうかを示すフラグ
	@Column(nullable = false)
    private boolean enabled;

    // アカウントの有効期限が切れているかどうかを示すフラグ
	@Column(nullable = false)
    private boolean accountNonExpired;

    // 資格情報の有効期限が切れているかどうかを示すフラグ
	@Column(nullable = false)
    private boolean accountNonLocked;

    // アカウントがロックされているかどうかを示すフラグ
	@Column(nullable = false)
    private boolean credentialsNonExpired;

	@Column(nullable = false)
    private long sortOrder;

    @Column
    private boolean isDeleted;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

	@Version
	@Column(nullable = false)
	private long timestamp;
    
    // ユーザーが持つ権限のリスト、DBからの取得用
    @OneToMany(mappedBy = "loginUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn
    private Set<LoginUserRole> loginUserRoles = new HashSet<>();
    
    // ユーザーが持つ権限のリスト、Userに渡すためのもの
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();        
        Set<LoginUserRole> loginUserRoles = getLoginUserRoles();

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

    public Set<LoginUserRole> getLoginUserRoles() {
        return loginUserRoles;
    }    
}
