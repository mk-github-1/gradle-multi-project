package com.example.domainmodel.entity.account;

import java.util.Date;

import jakarta.persistence.*;

/**
 * LoginUserRole
 *
 */
@Entity
@Table(name="login_user_role")
public class LoginUserRole {
	// 複合主キー
	@Id
	@Column(length = 256)
	private String username;

	@Id
	@Column(length = 256, nullable = false)
	private String role_id;

	@Column(nullable = false)
	private Date created_at;

	@Column(nullable = false)
	private Date updated_at;

	@Version
	@Column(nullable = false)
	private long timestamp;
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private LoginUser loginUser;
    
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;

	public String getRoleId() {
    	return role_id;
    }
}
