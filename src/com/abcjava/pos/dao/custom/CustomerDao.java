package com.abcjava.pos.dao.custom;

import com.abcjava.pos.dao.CrudDao;
import com.abcjava.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer,String> {
    public ArrayList<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException;
}
