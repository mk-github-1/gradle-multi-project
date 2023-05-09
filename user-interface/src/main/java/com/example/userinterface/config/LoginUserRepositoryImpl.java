package com.example.userinterface.config;

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

// コードはコンパイルを通すための下書きです。

/**
 * LoginUserRepositoryImpl
 *
 */
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
    public CompletableFuture<List<PersonModel>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PersonModel> criteriaQuery = criteriaBuilder.createQuery(PersonModel.class);

            Root<PersonModel> root = criteriaQuery.from(PersonModel.class);
            criteriaQuery.select(root);
            List<PersonModel> personModels = entityManager.createQuery(criteriaQuery).getResultList();
            entityManager.close();

            return personModels;
        });
    }

    public CompletableFuture<Optional<PersonModel>> findById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            PersonModel personModel = entityManager.find(PersonModel.class, id);
            entityManager.close();
            return Optional.ofNullable(personModel);
        });
    }

    public CompletableFuture<Void> create(PersonModel personModel) {
        return CompletableFuture.runAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(personModel);
            entityManager.getTransaction().commit();
            entityManager.close();
            return;
        }).thenAccept(result -> {
            // Do something with the result, if needed
        });
    }

    public CompletableFuture<Void> update(PersonModel personModel) {
        return CompletableFuture.runAsync(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(personModel);
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
            PersonModel personModel = entityManager.find(PersonModel.class, id);
            entityManager.remove(personModel);
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
            String sql = "DECLARE @temp AS TABLE ([id] BIGINT NOT NULL, [order] INT NOT NULL);\n";

            Enumeration<Long> keys = ids.keys();
            while (keys.hasMoreElements()) {
                Long id = keys.nextElement();
                Long order = ids.get(id);
                sql += "INSERT INTO @temp ([id], [order]) VALUES (" + id + ", " + order + ");\n";
            }

            sql += "UPDATE [dbo].[m_persons] " +
                    "SET [order] = B.[order] " +
                    "FROM [dbo].[m_persons] AS A " +
                    "LEFT OUTER JOIN ( " +
                    "  SELECT C.[id], ROW_NUMBER() OVER ( " +
                    "    ORDER BY " +
                    "      C.[is_deleted] ASC, " +
                    "      D.[order] ASC, " +
                    "      C.[updated_at] DESC " +
                    "  ) AS 'Order' " +
                    "  FROM [dbo].[m_persons] AS C " +
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
