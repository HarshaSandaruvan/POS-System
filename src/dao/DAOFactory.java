package dao;


import dao.custom.OrderDetailDAO;
import dao.custom.impl.*;

import static bo.BOFactory.BoTypes.LOGIN;

public class DAOFactory {
    private static DAOFactory daoFactory;
    public enum DAOTypes{
        ITEM, LOGIN, CUSTOMER, ORDERDETAIL, ORDERS
    }
    public static DAOFactory getDaoFactory(){
        if(daoFactory == null){
            return daoFactory = new DAOFactory();
        }else{
            return daoFactory;
        }
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case LOGIN:
                return new LoginDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case ORDERS:
                return new OrderDAOImpl();


            default:
                return null;
        }
    }

    private DAOFactory(){}

}
