package com.ecommerce.api.__constructs;

import com.ecommerce.api.utils.ApiResponse;

import java.util.List;

public interface CrudController<Model, Dto, ID> {

    ApiResponse<Model> create(Dto entity);

    ApiResponse<Model> getById(ID id);

    ApiResponse<List<Model>> getAll();

    ApiResponse<Model> update(ID id, Dto entity);

    ApiResponse<Boolean> delete(ID id);
}
