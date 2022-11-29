package com.abcjava.pos.bo.custom.impl;

import com.abcjava.pos.bo.custom.CustomerBo;
import com.abcjava.pos.dao.DaoFactory;
import com.abcjava.pos.dao.DaoTypes;
import com.abcjava.pos.dao.custom.CustomerDao;
import com.abcjava.pos.dto.CustomerDTO;
import com.abcjava.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoIMPL implements CustomerBo {

    CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomers(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> entityCustomerList = customerDao.searchCustomer(searchText);
        ArrayList<CustomerDTO> customerDTOList = new ArrayList<>();

        for(Customer c: entityCustomerList){
           CustomerDTO customerDTO= new CustomerDTO(
                   c.getId(), c.getName(), c.getAddress(), c.getSalary()
           );
           customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
