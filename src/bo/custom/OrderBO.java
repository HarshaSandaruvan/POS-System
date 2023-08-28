package bo.custom;

import bo.SuperBO;
import dto.OrdersDTO;
import javafx.collections.ObservableList;

public interface OrderBO extends SuperBO {
    public boolean saveOrders(OrdersDTO ordersDTO);

    public ObservableList<OrdersDTO> getAllOrders();
}
