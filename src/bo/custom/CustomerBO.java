package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer (CustomerDTO customerDTO);

    public String getNextCustomerId();
    public ObservableList<CustomerDTO> getAllCustomers();

    public boolean deleteCustomer(String customerId);
    public CustomerDTO getCustomerById(String customerId);
}
