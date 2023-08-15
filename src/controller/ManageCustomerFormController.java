package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);
    public void saveBtnOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO=new CustomerDTO(
                txtCustomerId.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtNicNumber.getText(),
                txtAddress.getText(),
                txtContactNo.getText()
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
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void editBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
    }
}
