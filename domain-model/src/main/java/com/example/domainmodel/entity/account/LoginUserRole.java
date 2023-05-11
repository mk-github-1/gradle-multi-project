package com.example.domainmodel.entity.account;

import java.time.OffsetDateTime;

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
	@Column(length = 32, nullable = false)
	private String roleId;

	@Column(nullable = false)
    private long sortOrder;

    @Column(nullable = false)
    private boolean isDeleted;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

	@Version
	@Column(nullable = false)
	private long timestamp;
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private LoginUser loginUser;
    
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", insertable = false, updatable = false)
    private Role role;

	public String getRoleId() {
    	return roleId;
    }
}
