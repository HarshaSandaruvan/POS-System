package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.PrintStream;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Customer customer =new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getNic(),
                customerDTO.getAddress(),
                customerDTO.getContactNo()
        );

       return customerDAO.saveCustomer(customer);
    }

    @Override
    public String getNextCustomerId() {
        String lastCustomerId = customerDAO.getLastCustomerId();
        int lastId = Integer.parseInt(lastCustomerId.substring(1));
        return String.format("C%03d",++lastId);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomers() {
        ArrayList<Customer> allCustomers = customerDAO.getAllCustomers();
        ObservableList<CustomerDTO> allCustomersForTable = FXCollections.observableArrayList();
        for (Customer c: allCustomers
        ) {
            allCustomersForTable.add(new CustomerDTO(
                   c.getCustomerId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getNic(),
                    c.getAddress(),
                    c.getContactNo()
            ));
        }

        return allCustomersForTable;
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return customerDAO.deleteCustomerById(customerId);

    }

    @Override
    public CustomerDTO getCustomerById(String customerId) {
        Customer customerById=customerDAO.getCustomerById(customerId);
        if (customerById!= null){
            return new CustomerDTO(
                    customerById.getCustomerId(),
                    customerById.getFirstName(),
                    customerById.getLastName(),
                    customerById.getNic(),
                    customerById.getAddress(),
                    customerById.getContactNo()
            );
        }
        return null;

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerDAO.updateCustomer(
                new Customer(
                        customerDTO.getCustomerId(),
                        customerDTO.getFirstName(),
                        customerDTO.getLastName(),
                        customerDTO.getNic(),
                        customerDTO.getAddress(),
                        customerDTO.getContactNo()
                )
        );
    }

    @Override
    public CustomerDTO getCustomerByContactNumber(String contactNumber) {
        Customer customerByContactNumber=customerDAO.getCustomerByContactNumber(contactNumber);

        if (customerByContactNumber!= null){
            return new CustomerDTO(
                    customerByContactNumber.getCustomerId(),
                    customerByContactNumber.getFirstName(),
                    customerByContactNumber.getLastName(),
                    customerByContactNumber.getNic(),
                    customerByContactNumber.getAddress(),
                    customerByContactNumber.getContactNo()
            );
        }
        return null;




    }


}
