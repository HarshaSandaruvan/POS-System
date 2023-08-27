package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Orders;

import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean saveOrder(Orders orders) {
        try {
            return CrudUtil.executeUpdate("INSERT INTO orders VALUES(?,?,?,?,?,?)",
                    orders.getOrderID(),
                    orders.getCustomerID(),
                    orders.getTime(),
                    orders.getDate(),
                    orders.getCashierID(),
                    orders.getTotal()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
