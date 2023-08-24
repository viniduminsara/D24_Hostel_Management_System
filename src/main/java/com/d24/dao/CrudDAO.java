package com.d24.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{

    boolean add(T entity) throws SQLException, IOException;

    boolean delete(ID id) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean exists(ID id) throws SQLException;

    List<T> getAll() throws SQLException, IOException;

}
