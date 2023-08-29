package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import entity.Customer;
import entity.OrderDetail;
import entity.Orders;

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
                    orderDetail.getPrice()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<OrderDetail> getOrderDetailByOrderId(String orderID) {
        ArrayList<OrderDetail>orderDetailsByOrderID=new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orderdetails WHERE orderID=?", orderID);
            while(resultSet.next()){
                orderDetailsByOrderID.add(new OrderDetail(
                        resultSet.getString("orderID"),
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("qty"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orderDetailsByOrderID;
    }

    @Override
    public boolean deleteOrderDetailByItemId(String itemId) {
        try {
            return CrudUtil.executeUpdate("DELETE FROM orderDetails WHERE itemId=?", itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
