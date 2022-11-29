package com.abcjava.pos.bo;

import com.abcjava.pos.bo.custom.impl.CustomerBoIMPL;
import com.abcjava.pos.bo.custom.impl.ItemBoIMPL;


public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return boFactory==null? new BoFactory():boFactory;
    }

    public <T>T getBo(BoTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (T) new CustomerBoIMPL();
            case ITEMS:
                return (T) new ItemBoIMPL();
            default:
                return null;
        }
    }
}
