package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean saveOrderDetail(OrderDetail orderDetail) {
        try {
            return CrudUtil.executeUpdate("INSERT INTO orderDetails VALUES(?,?,?,?,?,?)",
                    orderDetail.getOrderID(),
                    orderDetail.getItemID(),
                    orderDetail.getItemName(),
                    orderDetail.getQty(),
                    orderDetail.getUnitPrice(),
                    orderDetail.getDiscountedAmount()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getLastOrderId() {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT*FROM orders ORDER BY orderID DESC LIMIT 1");
            if(resultSet.next()){
                return resultSet.getString("orderID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "O0000";
    }

}
