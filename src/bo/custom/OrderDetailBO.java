package bo.custom;

import bo.SuperBO;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import javafx.collections.ObservableList;

public interface OrderDetailBO extends SuperBO {
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO);

    public ObservableList<OrderDetailDTO> getOrderDetailsByOrderID(String orderID);

    public boolean deleteOrderDetailByItemId(String itemID);
    public boolean deleteOrderDetailByOrderId(String orderID);

}
