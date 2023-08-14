package bo;

import bo.custom.impl.ItemBoImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        if (boFactory == null){
            return boFactory = new BOFactory();
        }else {
            return boFactory;
        }
    }
    public SuperBO getBo(BoTypes boTypes){
        switch (boTypes){
           case ITEM:
                return new ItemBoImpl();
            default:
                return null;
        }
    }

    public enum BoTypes{
        LOGIN, ITEM, CUSTOMER
    }


}
