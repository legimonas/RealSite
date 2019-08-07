package com.company.Dao;

public interface CrudDao<T> {
    boolean Save(T obj);
    boolean Update(T obj);
    void Delete(Integer id);

}
