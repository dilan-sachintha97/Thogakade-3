package com.abcjava.pos.bo.custom;

import com.abcjava.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> searchCustomers(String searchText) throws SQLException, ClassNotFoundException;
}
