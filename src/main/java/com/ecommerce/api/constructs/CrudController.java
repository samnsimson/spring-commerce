package com.ecommerce.api.constructs;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<T, ID> {
    ResponseEntity<T> create(T entity);

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(ID id);

    ResponseEntity<T> update(ID id, T entity);

    ResponseEntity<Void> delete(ID id);
}
