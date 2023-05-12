package com.example.domainservice.repository.account;

import java.util.*;

import com.example.domainmodel.entity.account.LoginUser;
// import com.example.domainservice.repository.GenericRepository;

public interface LoginUserRepository /* extends GenericRepository<LoginUser> */ {
    public Optional<LoginUser> findByUserName(String username);

    /*
    public CompletableFuture<List<LoginUserModel>> findAll();
    public CompletableFuture<Optional<LoginUserModel>> findById(String... ids);
    public CompletableFuture<Void> create(LoginUserModel loginUserModel);
    public CompletableFuture<Void> update(LoginUserModel loginUserModel);
    public CompletableFuture<Void> delete(Long id);
    public CompletableFuture<Void> sort(Dictionary<Long, Long> ids);
     */
}
