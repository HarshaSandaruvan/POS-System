package dao.custom;

import dao.SuperDAO;
import entity.Item;
import entity.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailDAO extends SuperDAO {
    public boolean saveOrderDetail(OrderDetail orderDetail);

    public ArrayList<OrderDetail> getOrderDetailByOrderId (String orderID);

    public boolean deleteOrderDetailByItemId(String itemId);
    public boolean deleteOrderDetailByOrderId(String orderID);





}
