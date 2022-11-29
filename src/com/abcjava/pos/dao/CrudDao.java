package com.abcjava.pos.dao;

public interface CrudDao <T,ID>{
    public boolean save(T t);
    public boolean update(T t);
    public boolean delete(ID id);
}
