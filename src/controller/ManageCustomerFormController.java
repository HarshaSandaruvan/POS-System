package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ManageCustomerFormController {
    public AnchorPane manageCustomerPane;
    public TextField txtCustomer;
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

    public void saveBtnOnAction(ActionEvent actionEvent) {
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void editBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
    }
}
