package com.abcjava.pos.dao;

import com.abcjava.pos.db.DBConnection;
import com.abcjava.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAccessCode {

    //save customer
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
       // database-code
        String sql = "INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,customer.getId());
        statement.setString(2,customer.getName());
        statement.setString(3,customer.getAddress());
        statement.setDouble(4,customer.getSalary());
        return statement.executeUpdate() > 0;
    }

    // update customer
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET name =?, address=?, salary=? WHERE id=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,customer.getName());
        statement.setString(2,customer.getAddress());
        statement.setDouble(3, customer.getSalary());
        statement.setString(4,customer.getId());
        return statement.executeUpdate() > 0;
    }
}
