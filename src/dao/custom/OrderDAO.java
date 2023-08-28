package dao.custom;

import dao.DAOFactory;
import dao.SuperDAO;
import entity.Orders;

import java.util.ArrayList;

public interface OrderDAO  extends SuperDAO {
    public boolean saveOrder(Orders orders);
    public ArrayList<Orders> getAllOrders();
}
