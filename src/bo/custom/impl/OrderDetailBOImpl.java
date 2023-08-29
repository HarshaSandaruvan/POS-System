package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import entity.OrderDetail;
import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    @Override
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail=new OrderDetail(
                orderDetailDTO.getOrderID(),
                orderDetailDTO.getItemID(),
                orderDetailDTO.getItemName(),
                orderDetailDTO.getQty(),
                orderDetailDTO.getUnitPrice(),
                orderDetailDTO.getPrice()
        );
        return orderDetailDAO.saveOrderDetail(orderDetail);
    }

    @Override
    public ObservableList<OrderDetailDTO> getOrderDetailsByOrderID(String orderID) {
        ArrayList<OrderDetail> orderDetailByOrderId = orderDetailDAO.getOrderDetailByOrderId(orderID);
        ObservableList<OrderDetailDTO> allOrderDetailsByOrderID = FXCollections.observableArrayList();
        for (OrderDetail o: orderDetailByOrderId
        ) {
            allOrderDetailsByOrderID.add(new OrderDetailDTO(
                    o.getOrderID(),
                    o.getItemID(),
                    o.getItemName(),
                    o.getQty(),
                    o.getUnitPrice(),
                    o.getPrice()
            ));
        }
        return allOrderDetailsByOrderID;

    }

    @Override
    public boolean deleteOrderDetailByItemId(String itemID) {
        return orderDetailDAO.deleteOrderDetailByItemId(itemID);

    }

    @Override
    public boolean deleteOrderDetailByOrderId(String orderID) {
        boolean b = orderDetailDAO.deleteOrderDetailByOrderId(orderID);
        System.out.println(b);
        return b;

    }


}
