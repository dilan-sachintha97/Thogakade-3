package com.abcjava.pos.dao;


import com.abcjava.pos.dao.custom.impl.CustomerDaoIMPL;
import com.abcjava.pos.dao.custom.impl.ItemDaoIMPL;

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
                return (T) new CustomerDaoIMPL();
            case ITEMS:
                return (T) new ItemDaoIMPL();
            default:
                return null;
        }
    }

}
