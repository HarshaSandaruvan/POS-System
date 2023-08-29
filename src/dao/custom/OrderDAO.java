package dao.custom;

import dao.DAOFactory;
import dao.SuperDAO;
import entity.Orders;

import java.sql.Date;
import java.util.ArrayList;

public interface OrderDAO  extends SuperDAO {
    public boolean saveOrder(Orders orders);
    public ArrayList<Orders> getAllOrders();

    public String getLastOrderId();

    public ArrayList<Orders> findOrdersByCustomerId(String customerID);
    public ArrayList<Orders> findOrdersByOrderId(String orderID);

    public ArrayList<Orders> findOrdersByDate(Date date);
    public ArrayList<Orders> findOrdersByCashierID(String itemID);


}
