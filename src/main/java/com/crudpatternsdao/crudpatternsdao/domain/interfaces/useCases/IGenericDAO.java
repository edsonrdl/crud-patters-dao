package com.crudpatternsdao.crudpatternsdao.domain.interfaces.useCases;

import java.util.List;

public interface IGenericDAO<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T entity);
    void update(T entity);
    void delete(ID id);
}
