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

import java.sql.Date;
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

    @Override
    public ObservableList<OrdersDTO> findOrdersByCustomerID(String customerID) {
        ArrayList<Orders> allOrders = orderDAO.findOrdersByCustomerId(customerID);
        ObservableList<OrdersDTO> allOrdersByCustomerID = FXCollections.observableArrayList();
        for (Orders o: allOrders
        ) {
            allOrdersByCustomerID.add(new OrdersDTO(
                    o.getOrderID(),
                    o.getCustomerID(),
                    o.getTime(),
                    o.getDate(),
                    o.getCashierID(),
                    o.getTotal()
            ));
        }
        return allOrdersByCustomerID;
    }

    @Override
    public ObservableList<OrdersDTO> findOrdersByOrderID(String orderID) {
        ArrayList<Orders> allOrders = orderDAO.findOrdersByOrderId(orderID);
        ObservableList<OrdersDTO> allOrdersByOrderID = FXCollections.observableArrayList();
        for (Orders o: allOrders
        ) {
            allOrdersByOrderID.add(new OrdersDTO(
                    o.getOrderID(),
                    o.getCustomerID(),
                    o.getTime(),
                    o.getDate(),
                    o.getCashierID(),
                    o.getTotal()
            ));
        }
        return allOrdersByOrderID;
    }

    @Override
    public ObservableList<OrdersDTO> findOrdersByDate(Date date) {
        ArrayList<Orders> allOrders = orderDAO.findOrdersByDate(date);
        ObservableList<OrdersDTO> allOrdersByDate = FXCollections.observableArrayList();
        for (Orders o: allOrders
        ) {
            allOrdersByDate.add(new OrdersDTO(
                    o.getOrderID(),
                    o.getCustomerID(),
                    o.getTime(),
                    o.getDate(),
                    o.getCashierID(),
                    o.getTotal()
            ));
        }
        return allOrdersByDate;
    }

    @Override
    public ObservableList<OrdersDTO> findOrdersByCashierID(String cashierID) {
        ArrayList<Orders> allOrders = orderDAO.findOrdersByCashierID(cashierID);
        ObservableList<OrdersDTO> allOrdersByCashierID = FXCollections.observableArrayList();
        for (Orders o: allOrders
        ) {
            allOrdersByCashierID.add(new OrdersDTO(
                    o.getOrderID(),
                    o.getCustomerID(),
                    o.getTime(),
                    o.getDate(),
                    o.getCashierID(),
                    o.getTotal()
            ));
        }
        return allOrdersByCashierID;
    }

    @Override
    public String getNextOrderID() {
        String lastItemId = orderDAO.getLastOrderId();
        int lastId = Integer.parseInt(lastItemId.substring(1));
        return String.format("O%04d",++lastId);

    }
}
