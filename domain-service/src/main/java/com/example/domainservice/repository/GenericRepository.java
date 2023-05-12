package com.example.domainservice.repository;

import java.util.Dictionary;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * GenericeRepository: Repositoryにロジックを含まないようにするため、メソッド固定をする予定
 *　
 */
public interface GenericRepository<T> {
	// 実際は検索条件が必要な場合が多い、マスタ程度であれば単純にfindAll?
	public CompletableFuture<List<T>> findAll();

	// このメソッドはサロゲートキー、ナチュラルキーの役割を兼ねる
	public CompletableFuture<Optional<T>> findById(String... ids);

	// 削除予定
	// public CompletableFuture<Optional<T>> findById(Long id);

	public CompletableFuture<Void> create(T model);

	public CompletableFuture<Void> update(T model);

	public CompletableFuture<Void> delete(Long id);

	public CompletableFuture<Void> sort(Dictionary<Long, Long> ids);
}
