package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import entity.Item;
import javafx.collections.ObservableList;
import lombok.ToString;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean saveCustomer(Customer customer) {
        try {
            return CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?)",
                    customer.getCustomerId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getNic(),
                    customer.getAddress(),
                    customer.getContactNo()

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getLastCustomerId() {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT*FROM customer ORDER BY customerID DESC LIMIT 1");
            if(resultSet.next()){
                return resultSet.getString("customerID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "C000";
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> allCustomers = new ArrayList<>();

        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer");

            while(resultSet.next()){
                allCustomers.add(new Customer(
                        resultSet.getString("customerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("nic"),
                        resultSet.getString("address"),
                        resultSet.getString("contactNumber")
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return allCustomers;
    }

    @Override
    public boolean deleteCustomerById(String customerId) {
        try {
           return CrudUtil.executeUpdate("DELETE FROM customer WHERE customerID=?", customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Customer getCustomerById(String customerId) {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer WHERE customerID=?", customerId);
            if (resultSet.next()){
                return new Customer(
                        resultSet.getString("customerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("nic"),
                        resultSet.getString("address"),
                        resultSet.getString("contactNumber")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            return CrudUtil.executeUpdate("UPDATE customer SET firstName=?, lastName=?,nic=?," +
                            "address=?,contactNumber=? WHERE customerID=?" ,
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getNic(),
                    customer.getAddress(),
                    customer.getContactNo(),
                    customer.getCustomerId()
                    );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer getCustomerByContactNumber(String contactNo) {

        try {
            ResultSet selectCustomer = CrudUtil.executeQuery("SELECT * FROM customer WHERE contactNumber=?;",contactNo);



            if (selectCustomer.first()){
                return new Customer(
                        selectCustomer.getString("customerID"),
                        selectCustomer.getString("firstName"),
                        selectCustomer.getString("lastName"),
                        selectCustomer.getString("nic"),
                        selectCustomer.getString("address"),
                        selectCustomer.getString("contactNumber")
                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


return null;



    }
}
