package com.ecommerce.api.constructs;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T create(T entity);

    List<T> getAll();

    Optional<T> getById(ID id);

    T update(ID id, T entity);

    ResponseEntity<Void> delete(ID id);
}
