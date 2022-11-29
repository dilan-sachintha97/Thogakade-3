package com.abcjava.pos.dao.custom;

import com.abcjava.pos.dao.CrudDao;
import com.abcjava.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item,String> {

    public ArrayList<Item> searchItem(String searchText) throws SQLException, ClassNotFoundException;
}
