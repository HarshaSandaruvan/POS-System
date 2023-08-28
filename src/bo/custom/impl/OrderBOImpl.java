package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dto.ItemDTO;
import dto.OrdersDTO;
import entity.Item;
import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    @Override
    public boolean saveOrders(OrdersDTO ordersDTO) {
        Orders orders=new Orders(
                ordersDTO.getOrderID(),
                ordersDTO.getCustomerID(),
                ordersDTO.getTime(),
                ordersDTO.getDate(),
                ordersDTO.getCashierID(),
                ordersDTO.getTotal()
        );
        return orderDAO.saveOrder(orders);
    }

    @Override
    public ObservableList<OrdersDTO> getAllOrders() {
        ArrayList<Orders> allOrders = orderDAO.getAllOrders();
        ObservableList<OrdersDTO> allOrdersForTable = FXCollections.observableArrayList();
        for (Orders o: allOrders
        ) {
            allOrdersForTable.add(new OrdersDTO(
                   o.getOrderID(),
                    o.getCustomerID(),
                    o.getTime(),
                    o.getDate(),
                    o.getCashierID(),
                    o.getTotal()
            ));
        }
        return allOrdersForTable;
    }
}
