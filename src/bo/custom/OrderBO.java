package bo.custom;

import bo.SuperBO;
import dto.OrdersDTO;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface OrderBO extends SuperBO {
    public boolean saveOrders(OrdersDTO ordersDTO);

    public ObservableList<OrdersDTO> getAllOrders();

    public String getNextOrderID();
    public ObservableList<OrdersDTO> findOrdersByCustomerID(String customerID);
    public ObservableList<OrdersDTO> findOrdersByOrderID(String orderID);
    public ObservableList<OrdersDTO> findOrdersByDate(Date date);
    public ObservableList<OrdersDTO> findOrdersByCashierID(String cashierID);


}
