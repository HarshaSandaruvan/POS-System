package controller;


import bo.BOFactory;
import bo.custom.OrderBO;

import bo.custom.OrderDetailBO;
import com.jfoenix.controls.JFXButton;
import dto.OrdersDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

import java.io.IOException;

import java.sql.Date;

public class OrderFormController {
    public TextField txtCustomerID;
    public MenuItem btnSearchCustomerId;
    public MenuItem btnSearchDate;
    public MenuItem btnSearchCashierID;
    public MenuButton btnSearchMenu;
    public MenuItem btnSearchOrderId;
    public TableColumn<?, ?> colCashierId;
    public TableColumn<?, ?> colCustomerId;
    public TableColumn<?, ?> colDate;
    public TableColumn<?, ?> colOrderID;
    public TableColumn<?, ?> colTime;
    public TableColumn<?, ?> colTotal;
    public TableView tblOrders;
    public TextField txtSearchText;
    public MenuItem btnSearchAllOrders;
    public JFXButton btnOderDelete;
    public JFXButton btnShowDetails;


    private ObservableList<OrdersDTO> allOrders;


    public int selectedIndex = -1;
    OrderBO orderBO= (OrderBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERS);
    OrderDetailBO orderDetailBO= (OrderDetailBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERDETAIL);

    public String selectedOrderID;
    public void initialize (){

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCashierId.setCellValueFactory(new PropertyValueFactory<>("cashierID"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        setDataToTable();

        tblOrders.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
            showOrderDetails();
        });

    }

    private void showOrderDetails()  {
        Parent parent = null;
        if(selectedIndex>-1){
            selectedOrderID=colOrderID.getCellObservableValue(selectedIndex).getValue().toString();
            try {

                OrderDetailsController.setOrderID(selectedOrderID);

                parent = FXMLLoader.load(this.getClass().getResource("../view/OrderDetailsPane.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene=new Scene(parent);
            Stage stage=new Stage();
            stage.setScene(scene);

            stage.setTitle("Order Details");
            stage.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please Select Order !");
            alert.show();
        }



    }

    private void setDataToTable() {
       allOrders=orderBO.getAllOrders();
       tblOrders.setItems(allOrders);
    }

    public void btnSearchCustomerIdOnAction(ActionEvent event) {

        btnSearchMenu.setText("Customer Id");
        txtSearchText.setPromptText("Enter Customer Id");
        btnSearchMenu.setStyle("-fx-text-fill: white");

        ObservableList<OrdersDTO> foundedOrders=orderBO.findOrdersByCustomerID(txtSearchText.getText());
        //tblOrders.getItems().clear();
        tblOrders.setItems(foundedOrders);


    }

    public void btnSearchDateOnAction(ActionEvent event) {
        btnSearchMenu.setText("Date");
        txtSearchText.setPromptText("Enter Date");
        btnSearchMenu.setStyle("-fx-text-fill: white");

        ObservableList<OrdersDTO> foundedOrders=orderBO.findOrdersByDate(Date.valueOf(txtSearchText.getText()));
        tblOrders.setItems(foundedOrders);


    }
    public void btnSearchCashierIdOnAction(ActionEvent event) {
        btnSearchMenu.setText("Item Id");
        txtSearchText.setPromptText("Enter Item Id");
        btnSearchMenu.setStyle("-fx-text-fill: white");

        ObservableList<OrdersDTO> foundedOrders=orderBO.findOrdersByCashierID(txtSearchText.getText());
        tblOrders.setItems(foundedOrders);

    }
    public void btnSearchOrderIdOnAction(ActionEvent event) {
        btnSearchMenu.setText("Order Id");
        txtSearchText.setPromptText("Enter Order Id");
        btnSearchMenu.setStyle("-fx-text-fill: white");

        ObservableList<OrdersDTO> foundedOrders=orderBO.findOrdersByOrderID(txtSearchText.getText());
        tblOrders.setItems(foundedOrders);

    }


    public void btnSearchAllOrdersOnAction(ActionEvent actionEvent) {
        setDataToTable();
    }


}
