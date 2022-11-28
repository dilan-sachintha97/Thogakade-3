package com.abcjava.pos.dao;

import com.abcjava.pos.db.DBConnection;
import com.abcjava.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    // delete customer
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql1 = "DELETE FROM Customer WHERE id=?";
        PreparedStatement statement1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
        statement1.setString(1,id);
        return statement1.executeUpdate() > 0;
    }

    //search customer
    public ArrayList<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE name LIKE ? || address LIKE ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,searchText);
        statement.setString(2,searchText);
        ResultSet set = statement.executeQuery();

        ArrayList<Customer> customerList = new ArrayList<>();

        while (set.next()){
            Customer customer = new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4)
            );
            customerList.add(customer);
        }
        return customerList;
    }
}
