package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.SQLException;

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
}
