package com.abcjava.pos.dao.custom.impl;

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
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, item.getCode());
        statement.setString(2, item.getDescription());
        statement.setDouble(3, item.getUnitPrice());
        statement.setInt(4, item.getQtyOnHand());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET description =?, unitPrice=?, qtyOnHand=? WHERE code=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, item.getDescription());
        statement.setDouble(2, item.getUnitPrice());
        statement.setInt(3, item.getQtyOnHand());
        statement.setString(4, item.getCode());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        String sql1 = "DELETE FROM Item WHERE code=?";
        PreparedStatement statement1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
        statement1.setString(1,code);
        return statement1.executeUpdate() > 0;
    }

    @Override
    public ArrayList<Item> searchItem(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Item WHERE description LIKE ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,searchText);
        ResultSet set = statement.executeQuery();

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
