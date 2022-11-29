package com.abcjava.pos.bo.custom;

import com.abcjava.pos.dto.CustomerDTO;
import com.abcjava.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo {
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> searchItems(String searchText) throws SQLException, ClassNotFoundException;
}
