package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBo;
import bo.custom.OrderBO;
import bo.custom.OrderDetailBO;
import com.jfoenix.controls.JFXButton;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import entity.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.ObjectPasser;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {

  
    
    public JFXButton btnClearOrder;
    public JFXButton btnItemRemove;
    public JFXButton btnFind;

    public JFXButton btnItemClear;
    public JFXButton btnAdd;
    public TextField txtDescription;
    public Label lblItemTotal;
    public TextField txtDiscounts;
    public TextField txtRequestQty;
    public Label lblBatchNo;
    public Label lblSupplier;
    public Label lblExpDate;
    public Label lblUnitPrice;
    public Label lblQty;
    public Label lblItemId;

    public TextField txtCustomerContactNo;
    public Label lblNumberOfItem;
    public Label lblTotal;
    public Label lblDiscount;
    public Label lblSubTotal;
    public Label lblOrderId;
    public TableView tblOrder;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colItemQty;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemPrice;
    public JFXButton btnCustomerClear;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerContNo;
    public TextField txtCustomerAddress;
    public TextField txtItemName;
    public JFXButton btnFindItem;
  
    public JFXButton buttonPay;
    public JFXButton btnPay;

    private int selectedIndex = -1;

   private  ButtonType YES;
    private ButtonType NO;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);
    ItemBo itemBo= (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);

    OrderBO orderBO= (OrderBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERS);
    ObservableList<OrderDetail> observableList= FXCollections.observableArrayList();

    OrderDetailBO orderDetailBO= (OrderDetailBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ORDERDETAIL);

    public void initialize(){
        generateAndSetOrderID();
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;

        });
    }

    private void generateAndSetOrderID() {
        lblOrderId.setText(orderBO.getNextOrderID());

    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        Time time = Time.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        Date date=Date.valueOf(LocalDate.now());

        //confirm Alert
        YES=new ButtonType("Yes");
        NO=new ButtonType("No");

        Alert confirmOrder =new Alert(Alert.AlertType.CONFIRMATION,"",YES,NO);
        confirmOrder.setHeaderText("Please Confirm the Order !");

        confirmOrder.showAndWait().ifPresent(response -> {
            if (response == YES) {
                //Save Order

                OrdersDTO ordersDTO = new OrdersDTO(
                        lblOrderId.getText(),
                        txtCustomerId.getText(),
                        time,
                        date,
                        ObjectPasser.userFullName,
                        Double.parseDouble(lblTotal.getText())
                );
                boolean isOrderSave = orderBO.saveOrders(ordersDTO);

                //Save Order Details

                int noOfItems = tblOrder.getItems().size();


                boolean isSaveOrderDetail = false;
                boolean isAllItemQtyUpdate=false;


                for (int i = 0; i < noOfItems; i++) {
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                            lblOrderId.getText(),
                            colItemId.getCellObservableValue(i).getValue().toString(),
                            colItemName.getCellObservableValue(i).getValue().toString(),
                            Double.parseDouble(colItemQty.getCellObservableValue(i).getValue().toString()),
                            Double.parseDouble(colItemUnitPrice.getCellObservableValue(i).getValue().toString()),
                            Double.parseDouble(colItemPrice.getCellObservableValue(i).getValue().toString())
                    );
                    isSaveOrderDetail = orderDetailBO.saveOrderDetail(orderDetailDTO);

                    String itemId=colItemId.getCellObservableValue(i).getValue().toString();
                    boolean isQtyUpdate=updateQtyByItemId(itemId, Double.parseDouble(colItemQty.getCellObservableValue(i).getValue().toString()));
                    if (!isQtyUpdate){
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Please check the Request Quantity of item "+itemId);
                        alert.show();
                        isAllItemQtyUpdate=false;
                    }else {
                        isAllItemQtyUpdate=true;
                    }
                }


                //

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (isOrderSave && isSaveOrderDetail && isAllItemQtyUpdate) {

                    tblOrder.getItems().clear();

                    generateAndSetOrderID();
                    alert.setHeaderText("Order saved !");
                    alert.show();
                } else {
                    alert.setHeaderText("Order not saved !");
                    alert.show();
                }

            }
        });
        }

    public void btnClearOrderOnAction(ActionEvent actionEvent) {
        clearItemTable();
        updateNumberOfItems();

    }

    private void clearItemTable() {
        YES=new ButtonType("Yes");
        NO=new ButtonType("No");

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"",YES,NO);
        alert.setHeaderText("Do you want Delete this Order ?");

        alert.showAndWait().ifPresent(response ->{
            if (response==YES){
                tblOrder.getItems().clear();

                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("Order is Deleted !");
                alert1.showAndWait();
            }
        });
        updateSumOfTotal();
    }

    public void btnItemRemoveOnAction(ActionEvent actionEvent) {
       YES=new ButtonType("Yes");
       NO=new ButtonType("No");

        if (selectedIndex<0){
            Alert alert1 =new Alert(Alert.AlertType.WARNING);
            alert1.setHeaderText("Please select the item first !");
            alert1.showAndWait();
        }else {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"",YES,NO);
            alert.setHeaderText("Do you want Remove this Item ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == YES) {
                    tblOrder.getItems().remove(selectedIndex);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText("Item is Remove !");
                    alert1.showAndWait();
                }
            });
        }

        updateNumberOfItems();
        updateSumOfTotal();

    }

    public void updateNumberOfItems() {

        lblNumberOfItem.setText(String.valueOf(tblOrder.getItems().size()));
    }

   public void updateSumOfTotal(){
    double total=0.0;
    for (OrderDetail orderDetail:observableList){
        total+=orderDetail.getPrice();
    }

    lblSubTotal.setText(String.valueOf(total));
    updateDiscount();
       updateTotal();
   }

   public void updateDiscount(){
          if (txtDiscounts.getText()!=null){
            lblDiscount.setText(String.valueOf(Double.parseDouble(lblSubTotal.getText())*Double.parseDouble(txtDiscounts.getText())/100));
        }
   }

   public void updateTotal(){
        lblTotal.setText(String.valueOf(Double.parseDouble(lblSubTotal.getText())-Double.parseDouble(lblDiscount.getText())));
   }

    public void btnFindOnAction(ActionEvent actionEvent) {
        //Load Customer Details to Dashboard

        CustomerDTO customerByContactNumber = customerBO.getCustomerByContactNumber(txtCustomerContactNo.getText());


        if (customerByContactNumber!=null){
            txtCustomerId.setText(customerByContactNumber.getCustomerId());
            txtCustomerName.setText(customerByContactNumber.getFirstName()+" "+customerByContactNumber.getLastName());
            txtCustomerAddress.setText(customerByContactNumber.getAddress());
            txtCustomerContNo.setText(customerByContactNumber.getContactNo());

        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please Check the Contact Number !");
            alert.show();
        }



    }

    public void btnItemClearOnAction(ActionEvent actionEvent) {
        clearItemFields();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        observableList.add(new OrderDetail(
                lblOrderId.getText(),
                lblItemId.getText(),
                txtItemName.getText(),
                Double.parseDouble(txtRequestQty.getText()),
                Double.parseDouble(lblUnitPrice.getText()),
                Double.parseDouble(txtRequestQty.getText())* Double.parseDouble(lblUnitPrice.getText())
        ));

    tblOrder.setItems(observableList);

    updateNumberOfItems();
    updateSumOfTotal();
    clearItemFields();

    }

    public void btnCustomerClearOnAction(ActionEvent actionEvent) {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerContNo.clear();
    }

    public void btnFindItemOnAction(ActionEvent actionEvent) {
        ItemDTO itemByItemName = itemBo.getItemByItemName(txtItemName.getText());
        if (itemByItemName != null) {
            lblItemId.setText(itemByItemName.getItemID());
            lblBatchNo.setText(itemByItemName.getBatchNumber());
            lblQty.setText(String.valueOf(itemByItemName.getQty()));
            lblUnitPrice.setText(String.valueOf(itemByItemName.getPrice()));
            lblExpDate.setText(String.valueOf(itemByItemName.getExpireDate()));
            lblSupplier.setText(itemByItemName.getSupplier());
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please Check Item Name !");
            alert.show();
        }
    }

    private void clearItemFields(){
        txtItemName.clear();
        lblItemId.setText("");
        lblBatchNo.setText("");
        lblQty.setText("");
        lblUnitPrice.setText("");
        lblExpDate.setText("");
        lblSupplier.setText("");
        txtRequestQty.clear();
    }

    public boolean updateQtyByItemId(String itemId, double requestQty) {
        double qtyOnHand = itemBo.getQtyByItemId(itemId);
        if (qtyOnHand >= requestQty) {
            double qty = qtyOnHand - requestQty;
            return itemBo.updateQtyByItemId(itemId, qty);
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please check the Request Quantity of Item "+itemId);
        }
        return false;
    }
}
