package com.example.domainmodel.entity.account;

import java.util.*;

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
	private String role_id;

	@Column(nullable = false)
	private Date created_at;

	@Column(nullable = false)
	private Date updated_at;

	@Version
	@Column(nullable = false)
	private long timestamp;
}
