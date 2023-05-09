package com.example.domainservice.repository;

import java.util.Dictionary;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * GenericeRepository
 *　
 */
public interface GenericRepository<T> {
	public CompletableFuture<List<T>> findAll();

	public CompletableFuture<Optional<T>> findById(Long id);

	public CompletableFuture<Void> create(T model);

	public CompletableFuture<Void> update(T model);

	public CompletableFuture<Void> delete(Long id);

	public CompletableFuture<Void> sort(Dictionary<Long, Long> ids);
}
