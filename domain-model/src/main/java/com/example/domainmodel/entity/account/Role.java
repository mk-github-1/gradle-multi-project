package com.example.domainmodel.entity.account;

import java.time.OffsetDateTime;

import jakarta.persistence.*;

/**
 * Role
 *
 */
@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(length = 32)
	private String roleId;

	@Column(length = 256, nullable = false)
	private String roleName;

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
}
