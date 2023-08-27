package bo.custom;

import bo.SuperBO;
import dto.OrdersDTO;

public interface OrderBO extends SuperBO {
    public boolean saveOrders(OrdersDTO ordersDTO);
}
