package com.abcjava.pos.dao;

import com.abcjava.pos.dao.custom.impl.CustomerDaoImpl;
import com.abcjava.pos.dao.custom.impl.ItemDaoImpl;

public class DaoFactory {
    // Applied Factory Method Design pattens
    private static DaoFactory daoFactory;

    private DaoFactory(){}


    public  static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()) : daoFactory;
    }
    // singleton
    //====================================================
    //Factory

    public <T>T getDao(DaoTypes type){

        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEMS:
                return (T) new ItemDaoImpl();
            default:
                return null;
        }
    }

}
