package com.abcjava.pos.dao.custom.impl;

import com.abcjava.pos.dao.custom.ItemDao;
import com.abcjava.pos.db.DBConnection;
import com.abcjava.pos.entity.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
