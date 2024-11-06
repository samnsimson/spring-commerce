package com.ecommerce.api.constructs;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T create(T entity);

    List<T> getAll();

    Optional<T> getById(ID id);

    T update(ID id, T entity);

    void delete(ID id);
}
