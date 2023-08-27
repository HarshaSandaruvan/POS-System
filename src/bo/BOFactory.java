package bo;


import bo.custom.OrderBO;
import bo.custom.impl.*;

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
            case LOGIN:
                return new LoginBoImpl();
           case ITEM:
                return new ItemBoImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
            case ORDERS:
                return new OrderBOImpl();
                default:
                return null;
        }
    }

    public enum BoTypes{
        LOGIN, ITEM, CUSTOMER, ORDERDETAIL, ORDERS
    }


}
