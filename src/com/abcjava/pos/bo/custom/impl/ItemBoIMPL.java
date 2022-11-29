package com.abcjava.pos.bo.custom.impl;

import com.abcjava.pos.bo.custom.ItemBo;
import com.abcjava.pos.dao.DaoFactory;
import com.abcjava.pos.dao.DaoTypes;
import com.abcjava.pos.dao.custom.ItemDao;
import com.abcjava.pos.dto.ItemDTO;
import com.abcjava.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoIMPL implements ItemBo {
    ItemDao itemDao = DaoFactory.getInstance().getDao(DaoTypes.ITEMS);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item( itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.delete(code);
    }

    @Override
    public ArrayList<ItemDTO> searchItems(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemArrayList = itemDao.searchItem(searchText);
        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();

        for(Item i :itemArrayList ){
            ItemDTO itemDTO = new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
            itemDTOArrayList.add(itemDTO);
        }
        return itemDTOArrayList;
    }
}
