package com.abcjava.pos.dao.custom.impl;

import com.abcjava.pos.dao.CrudUtil;
import com.abcjava.pos.dao.custom.ItemDao;
import com.abcjava.pos.db.DBConnection;
import com.abcjava.pos.entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Item VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET description =?, unitPrice=?, qtyOnHand=? WHERE code=?";
        return CrudUtil.execute(sql,item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public ArrayList<Item> searchItem(String searchText) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item WHERE description LIKE ?",searchText);

        ArrayList<Item> itemArrayList = new ArrayList<>();
        while (set.next()){
            Item item = new Item(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)
            );
            itemArrayList.add(item);
        }
        return itemArrayList;
    }
}
