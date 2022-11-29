package com.abcjava.pos.dao.custom.impl;

import com.abcjava.pos.dao.CrudUtil;
import com.abcjava.pos.dao.custom.CustomerDao;
import com.abcjava.pos.db.DBConnection;
import com.abcjava.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET name =?, address=?, salary=? WHERE id=?";
        return CrudUtil.execute(sql, customer.getName(),customer.getAddress(),customer.getSalary(),customer.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql1 = "DELETE FROM Customer WHERE id=?";
        return CrudUtil.execute(sql1,id);
    }

    @Override
    public ArrayList<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE name LIKE ? || address LIKE ?";
        ResultSet set = CrudUtil.execute(sql,searchText,searchText);

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
