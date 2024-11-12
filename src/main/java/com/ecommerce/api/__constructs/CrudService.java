package com.ecommerce.api.__constructs;

import java.util.List;

public interface CrudService<T, K, ID> {

    T create(K entity);

    T getById(ID id);

    List<T> getAll();

    T update(ID id, K entity);

    boolean delete(ID id);
}
