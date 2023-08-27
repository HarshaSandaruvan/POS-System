package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderFormController {
    public TextField txtCustomerID;
    public MenuItem btnSearchCustomerId;
    public MenuItem btnSearchDate;
    public MenuItem btnSearchItemId;
    public MenuButton btnSearchMenu;
    public MenuItem btnSearchOrderId;
    public TableColumn<?, ?> colCashierId;
    public TableColumn<?, ?> colCustomerId;
    public TableColumn<?, ?> colDate;
    public TableColumn<?, ?> colOrdI;
    public TableColumn<?, ?> colTime;
    public TableColumn<?, ?> colTotal;
    public TableView<?> tblOrders;
    public TextField txtSearchText;

   
    public void btnSearchCustomerIdOnAction(ActionEvent event) {

    }

    public void btnSearchDateOnAction(ActionEvent event) {

    }
    public void btnSearchItemIdOnAction(ActionEvent event) {

    }
    public void btnSearchOrderIdOnAction(ActionEvent event) {

    }



}
