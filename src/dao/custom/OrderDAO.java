package dao.custom;

import dao.DAOFactory;
import dao.SuperDAO;
import entity.Orders;

public interface OrderDAO  extends SuperDAO {
    public boolean saveOrder(Orders orders);
}
