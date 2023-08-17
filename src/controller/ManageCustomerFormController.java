package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xpath.internal.operations.Or;
import dto.CustomerDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.PrintStream;

public class ManageCustomerFormController {
    public AnchorPane manageCustomerPane;
    public TextField txtCustomerId;
    public TextField txtFirstName;
    public TextField txtNicNumber;
    public TextField txtAddress;
    public TextField txtContactNo;
    public TextField txtLastName;
    public JFXButton btnSave;
    public JFXButton btnClear;
    public JFXButton btnEdit;
    public JFXButton btnDelete;
    public TableView tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colContactNo;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colNicNumber;
    public TableColumn colAddress;
    public TableColumn colCusID;
    public TableColumn colNumber;
    private int selectedIndex = -1;

    private ObservableList<CustomerDTO> allCustomers;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);

    public void initialize (){
        generateAndSetCustomerId();
    }

    private void generateAndSetCustomerId() {
        txtCustomerId.setText( customerBO.getNextCustomerId());



        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNicNumber.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));




        setDataToTable();


        tblCustomer.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }

    private void setDataToTable() {
        allCustomers=customerBO.getAllCustomers();
        tblCustomer.setItems(allCustomers);



    }


    public void saveBtnOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO=new CustomerDTO(
                txtCustomerId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtNicNumber.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtContactNo.getText())
        );
        boolean b = customerBO.saveCustomer(customerDTO);
        Alert alert;
        if(b){
            alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Customer Saved !");
            alert.show();
        }else {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Customer Not Saved !");
            alert.show();
        }

        setDataToTable();
        generateAndSetCustomerId();
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {

        ButtonType YES=new ButtonType("Yes");
        ButtonType NO=new ButtonType("No");


        Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"",YES,NO);
        alert.setHeaderText("Do you want Delete this Customer ?");
        alert.showAndWait().ifPresent(response -> {
            if (response==YES){
                if (selectedIndex != -1) {

                    CustomerDTO selectedCustomer = allCustomers.get(selectedIndex);
                    CustomerDTO customerById = customerBO.getCustomerById(selectedCustomer.getCustomerId());
                    System.out.println(selectedCustomer.getCustomerId());

                    boolean isDelete = customerBO.deleteCustomer(customerById.getCustomerId());
                    System.out.println(customerById.getCustomerId());
                    setDataToTable();
                    Alert alert1;
                    if (isDelete){
                        alert1=new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Delete Customer");
                        alert1.setHeaderText("Customer Delete SuccessFull !");
                        alert1.show();
                    }else {
                        alert1=new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Delete Customer");
                        alert1.setHeaderText("Customer Delete Unsuccessful !");
                        alert1.show();
                    }
                }else {
                    Alert alert3=new Alert(Alert.AlertType.ERROR);
                    alert3.setHeaderText("Please select Customer First !");
                    alert3.show();

                }
            }
        });

    }

    public void editBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
    }
}
