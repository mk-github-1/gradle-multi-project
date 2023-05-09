package com.example.domainservice.repository.account;

import java.util.*;

import com.example.domainmodel.entity.account.LoginUser;
// import com.example.domainservice.repository.GenericRepository;

public interface LoginUserRepository /* extends GenericRepository<LoginUser> */ {
    public Optional<LoginUser> findByUserName(String username);
}