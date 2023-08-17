package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xpath.internal.operations.Or;
import dto.CustomerDTO;
import dto.ItemDTO;
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

    private boolean isEdit=false;
    private int selectedIndex = -1;

    private ObservableList<CustomerDTO> allCustomers;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);

    public void initialize(){
        generateAndSetCustomerId();

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

    private void generateAndSetCustomerId() {
        txtCustomerId.setText( customerBO.getNextCustomerId());

    }

    private void setDataToTable() {
        allCustomers=customerBO.getAllCustomers();
        tblCustomer.setItems(allCustomers);



    }


    public void saveBtnOnAction(ActionEvent actionEvent) {
        if(!isEdit){
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
            clear();
        }else {
            boolean isUpdate = customerBO.updateCustomer(new CustomerDTO(
                    txtCustomerId.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtNicNumber.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContactNo.getText())

            ));

            Alert alert;
           if (isUpdate){
               alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Customer Update !");
               alert.setHeaderText("Update SuccessFull !");
               alert.show();

               btnSave.setText("Save");
               btnSave.setStyle("-fx-background-color:  #16a085");
               isEdit = false;
           }else {
               alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Customer Update !");
               alert.setHeaderText("Update Unsuccessful !");
               alert.show();
           }

        }
        setDataToTable();
        clear();
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
        if(selectedIndex != -1){
            loadCustomerDataToFields(allCustomers.get(selectedIndex));
            btnSave.setText("Update");
            btnSave.setStyle("-fx-background-color:  #f1c40f");
            isEdit = true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select Customer first.");
            alert.show();
        }
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
           clear();
    }

    public void clear(){
        txtCustomerId.clear();
        txtContactNo.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtNicNumber.clear();
        txtAddress.clear();

        generateAndSetCustomerId();
    }
    public void loadCustomerDataToFields(ItemDTO selectedCustomer){
        CustomerDTO selectedCustomerDetail = allCustomers.get(selectedIndex);
        CustomerDTO customerById = customerBO.getCustomerById(selectedCustomerDetail.getCustomerId());

        txtCustomerId.setText(customerById.getCustomerId());
        txtContactNo.setText(String.valueOf(customerById.getContactNo()));
        txtFirstName.setText(customerById.getFirstName());
        txtLastName.setText(customerById.getLastName());
        txtNicNumber.setText(customerById.getNic());
        txtAddress.setText(customerById.getAddress());
    }

}
