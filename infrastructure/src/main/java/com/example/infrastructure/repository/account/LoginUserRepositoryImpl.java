package com.example.infrastructure.repository.account;

import java.util.*;
// import java.util.concurrent.CompletableFuture;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
// import jakarta.persistence.Query;
// import jakarta.persistence.criteria.CriteriaBuilder;
// import jakarta.persistence.criteria.CriteriaQuery;
// import jakarta.persistence.criteria.Root;

// import org.springframework.boot.context.properties.EnableConfigurationProperties;
// import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.domainmodel.entity.account.LoginUser;
import com.example.domainservice.repository.account.LoginUserRepository;

// コードはコンパイルを通すための下書きです。

// @Async
@Repository
public class LoginUserRepositoryImpl implements LoginUserRepository {

    private final EntityManagerFactory entityManagerFactory;

    public LoginUserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Optional<LoginUser> findByUserName(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        LoginUser loginUser = entityManager.find(LoginUser.class, username);
        entityManager.close();
        return Optional.ofNullable(loginUser);
    }

    /*
    public CompletableFuture<List<LoginUserModel>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<LoginUserModel> criteriaQuery = criteriaBuilder.createQuery(LoginUserModel.class);

            Root<LoginUserModel> root = criteriaQuery.from(LoginUserModel.class);
            criteriaQuery.select(root);
            List<LoginUserModel> loginUserModels = entityManager.createQuery(criteriaQuery).getResultList();
            entityManager.close();

            return loginUserModels;
        });
    }

    public CompletableFuture<Optional<LoginUserModel>> findById(String... id) {
        return CompletableFuture.supplyAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            
            // (int)id[0]でデータ取得できるかは未確認
            LoginUserModel loginUserModel = entityManager.find(LoginUserModel.class, (int)id[0]);
            
            entityManager.close();
            return Optional.ofNullable(loginUserModel);
        });
    }

    public CompletableFuture<Void> create(LoginUserModel loginUserModel) {
        return CompletableFuture.runAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(loginUserModel);
            entityManager.getTransaction().commit();
            entityManager.close();
            return;
        }).thenAccept(result -> {
            // Do something with the result, if needed
        });
    }

    public CompletableFuture<Void> update(LoginUserModel loginUserModel) {
        return CompletableFuture.runAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(loginUserModel);
            entityManager.getTransaction().commit();
            entityManager.close();
            return;
        }).thenAccept(result -> {
            // Do something with the result, if needed
        });
    }

    public CompletableFuture<Void> delete(Long id) {
        return CompletableFuture.runAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            LoginUserModel loginUserModel = entityManager.find(LoginUserModel.class, id);
            entityManager.remove(loginUserModel);
            entityManager.getTransaction().commit();
            entityManager.close();
            return;
        }).thenAccept(result -> {
            // Do something with the result, if needed
        });
    }

    public CompletableFuture<Void> sort(Dictionary<Long, Long> ids) {
        return CompletableFuture.runAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            String sql = "DECLARE @temp AS TABLE ([id] BIGINT NOT NULL, [sort_order] INT NOT NULL);\n";

            Enumeration<Long> keys = ids.keys();
            while (keys.hasMoreElements()) {
                Long id = keys.nextElement();
                Long sort_order = ids.get(id);
                sql += "INSERT INTO @temp ([id], [sort_order]) VALUES (" + id + ", " + sort_order + ");\n";
            }

            sql += "UPDATE [dbo].[m_login_users] " +
                    "SET [sort_order] = B.[sort_order] " +
                    "FROM [dbo].[m_login_users] AS A " +
                    "LEFT OUTER JOIN ( " +
                    "  SELECT C.[id], ROW_NUMBER() OVER ( " +
                    "    ORDER BY " +
                    "      C.[is_deleted] ASC, " +
                    "      D.[sort_order] ASC, " +
                    "      C.[updated_at] DESC " +
                    "  ) AS 'Order' " +
                    "  FROM [dbo].[m_login_users] AS C " +
                    "  LEFT OUTER JOIN @temp AS D " +
                    "  ON C.[Id] = D.[Id] " +
                    ") AS B " +
                    "ON A.[Id] = B.[Id] " +
                    "WHERE B.[Id] IS NOT NULL";

            Query query = entityManager.createNativeQuery(sql);
            query.executeUpdate();

            entityManager.close();

            return;
        });
    }
     */
}
