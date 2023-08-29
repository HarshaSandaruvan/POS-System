package controller;

import bo.BOFactory;
import bo.custom.OrderDetailBO;
import com.jfoenix.controls.JFXButton;
import dto.OrderDetailDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.chart.AxisBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class OrderDetailsController {


    public JFXButton btnDeleteOrderItem;
    public TableColumn<?, ?> colItemId;
    public TableColumn<?, ?> colItemName;
    public TableColumn<?, ?> colPrice;
    public TableColumn<?, ?> colQty;
    public TableColumn<?, ?> colUnitPrice;
    public Label lblOrderId;
    public TableView<OrderDetailDTO> tblOrderDetails;
    private ObservableList<OrderDetailDTO> orderDetailsByOrderID;
    OrderDetailBO orderDetailBO= (OrderDetailBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERDETAIL);

    public int selectedIndex=-1;
     static String selectedOrderID;

    public void initialize () {

        System.out.println("Initialize");
        System.out.println("selected id"+selectedOrderID);


        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        lblOrderId.setText(selectedOrderID);
        loadDataToTable(selectedOrderID);

        tblOrderDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }
    public void btnDeleteOrderItemOnAction(ActionEvent event) {
        String selectedItemId=colItemId.getCellObservableValue(selectedIndex).getValue().toString();
        boolean isItemDetailDelete = orderDetailBO.deleteOrderDetailByItemId(selectedItemId);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        if (isItemDetailDelete){
            alert.setHeaderText("Item Detail is Delete Successful.");
        }else {
            alert.setHeaderText("Item Detail is Delete Unsuccessful.");
        }
        alert.show();
        loadDataToTable(selectedOrderID);
    }
    public  void loadDataToTable(String orderID){

        orderDetailsByOrderID = orderDetailBO.getOrderDetailsByOrderID(orderID);
        tblOrderDetails.setItems(orderDetailsByOrderID);
    }

    public static void setOrderID(String orderID){
        selectedOrderID=orderID;
    }
}
