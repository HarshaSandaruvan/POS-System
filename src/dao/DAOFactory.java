package dao;

import dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    public enum DAOTypes{
        ITEM
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
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }

    private DAOFactory(){}

}
