package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dto.OrdersDTO;
import entity.Orders;



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
}
