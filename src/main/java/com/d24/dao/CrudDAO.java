package com.d24.dao;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{

    boolean add(T entity);

    boolean delete(ID id);

    boolean update(T entity);

    boolean exists(ID id);

    List<T> getAll();

}
