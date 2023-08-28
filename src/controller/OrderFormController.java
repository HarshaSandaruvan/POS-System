package controller;


import bo.BOFactory;
import bo.custom.OrderBO;
import dto.OrdersDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public TableView tblOrders;
    public TextField txtSearchText;

    private ObservableList<OrdersDTO> allOrders;
   
    OrderBO orderBO= (OrderBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERS);

    public void initialize (){

        colOrdI.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCashierId.setCellValueFactory(new PropertyValueFactory<>("cashierID"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        setDataToTable();


    }

    private void setDataToTable() {
       allOrders=orderBO.getAllOrders();
       tblOrders.setItems(allOrders);
    }

    public void btnSearchCustomerIdOnAction(ActionEvent event) {

    }

    public void btnSearchDateOnAction(ActionEvent event) {

    }
    public void btnSearchItemIdOnAction(ActionEvent event) {

    }
    public void btnSearchOrderIdOnAction(ActionEvent event) {

    }





}
