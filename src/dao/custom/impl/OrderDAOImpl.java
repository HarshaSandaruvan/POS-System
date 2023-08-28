package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Customer;
import entity.Item;
import entity.Orders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public ArrayList<Orders> getAllOrders() {
        ArrayList<Orders> allOrders = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders");
            while(resultSet.next()){
                allOrders.add(new Orders(
                        resultSet.getString("orderID"),
                        resultSet.getString("customerID"),
                        resultSet.getTime("time"),
                        resultSet.getDate("date"),
                        resultSet.getString("cashierID"),
                        resultSet.getDouble("total")
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return allOrders;
    }

    @Override
    public ArrayList<Orders> findOrdersByCustomerId(String customerID) {
        ArrayList<Orders>foundOrders=new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE customerID=?", customerID);
            while(resultSet.next()){
                foundOrders.add(new Orders(
                        resultSet.getString("orderID"),
                        resultSet.getString("customerID"),
                        resultSet.getTime("time"),
                        resultSet.getDate("date"),
                        resultSet.getString("cashierID"),
                        resultSet.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return foundOrders;
    }

    @Override
    public ArrayList<Orders> findOrdersByOrderId(String orderID) {
        ArrayList<Orders>foundOrders=new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE orderID=?", orderID);
            while(resultSet.next()){
                foundOrders.add(new Orders(
                        resultSet.getString("orderID"),
                        resultSet.getString("customerID"),
                        resultSet.getTime("time"),
                        resultSet.getDate("date"),
                        resultSet.getString("cashierID"),
                        resultSet.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return foundOrders;
    }

    @Override
    public ArrayList<Orders> findOrdersByDate(Date date) {
        ArrayList<Orders>foundOrders=new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE date=?", date);
            while(resultSet.next()){
                foundOrders.add(new Orders(
                        resultSet.getString("orderID"),
                        resultSet.getString("customerID"),
                        resultSet.getTime("time"),
                        resultSet.getDate("date"),
                        resultSet.getString("cashierID"),
                        resultSet.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return foundOrders;
    }

    @Override
    public ArrayList<Orders> findOrdersByCashierID(String cashierID) {
        ArrayList<Orders>ordersByCustomerId=new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE cashierID=?", cashierID);
            while(resultSet.next()){
                ordersByCustomerId.add(new Orders(
                        resultSet.getString("orderID"),
                        resultSet.getString("customerID"),
                        resultSet.getTime("time"),
                        resultSet.getDate("date"),
                        resultSet.getString("cashierID"),
                        resultSet.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ordersByCustomerId;
    }
}
