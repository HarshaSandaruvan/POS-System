package dao;

import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.LoginDAOImpl;

import static bo.BOFactory.BoTypes.LOGIN;

public class DAOFactory {
    private static DAOFactory daoFactory;
    public enum DAOTypes{
        ITEM, LOGIN
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
            default:
                return null;
        }
    }

    private DAOFactory(){}

}
