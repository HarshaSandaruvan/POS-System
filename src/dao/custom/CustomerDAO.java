package dao.custom;

import dao.SuperDAO;
import entity.Customer;
import entity.Item;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface CustomerDAO  extends SuperDAO {
    public boolean saveCustomer(Customer customer);
    public String getLastCustomerId();
    public ArrayList<Customer> getAllCustomers();
    public boolean deleteCustomerById(String customerId);

    public Customer getCustomerById(String customerId);
    public boolean updateCustomer(Customer customer);

    public Customer getCustomerByContactNumber(String contactNo);
}
