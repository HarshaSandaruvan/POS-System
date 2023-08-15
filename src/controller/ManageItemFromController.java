package controller;

import bo.BOFactory;
import bo.custom.ItemBo;

import com.jfoenix.controls.JFXButton;
import dto.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


import java.sql.Date;
import java.time.LocalDate;

public class ManageItemFromController {
    public AnchorPane addItemAnchorPane;
    public TextField txtItemName;
    public TextField txtBatchNumber;

    public TextField txtQtyOnHand;
    public TextField txtSupplier;
    public DatePicker datePicker;
    public JFXButton btnSave;
    public TableView itemsTbl;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colSupplier;
    public TableColumn colBatchNumber;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colDate;
    public JFXButton btnClear;
    public JFXButton btnEdit;
    public JFXButton btnDelete;
    public TextField txtItemId;
    public TextField txtItemPrice;
    private ObservableList<ItemDTO> allItems;
    private int selectedIndex = -1;
    private boolean isEdit = false;

    ItemBo itemBO = (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);

    public void initialize (){
       generateAndSetItemId();
    }

    private void generateAndSetItemId() {
        txtItemId.setText(itemBO.getNextId());

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        setDataToTable();

        // Set listener to the table
        itemsTbl.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }


    public void clearBtnOnAction(ActionEvent actionEvent) {
        txtBatchNumber.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtSupplier.clear();
        txtQtyOnHand.clear();
        datePicker.getEditor().clear();
    }

    public void editBtnOnAction(ActionEvent actionEvent) {
        if(selectedIndex != -1){
            loadItemDataToFields(allItems.get(selectedIndex));
            btnSave.setText("UPDATE");
            btnSave.setStyle("-fx-background-color:  #f1c40f");
            isEdit = true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select item first.");
            alert.show();
        }

    }

    private void loadItemDataToFields(ItemDTO selectedItem) {
        ItemDTO selectedItemDetails = itemBO.getItemById(selectedItem.getItemID());
        txtSupplier.setText(selectedItemDetails.getSupplier());
        txtBatchNumber.setText(selectedItemDetails.getBatchNumber());
        txtItemId.setText(selectedItemDetails.getItemID());
        txtItemPrice.setText(String.format("%.2f",selectedItemDetails.getPrice()));
        datePicker.getEditor().setText(String.valueOf(selectedItemDetails.getExpireDate()));
        txtItemName.setText(selectedItemDetails.getItemName());
        txtQtyOnHand.setText(String.format("%.2f",selectedItemDetails.getQty()));

    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {

        ButtonType YES=new ButtonType("Yes");
        ButtonType NO=new ButtonType("No");


        Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"",YES,NO);
        alert.setHeaderText("Do you want Delete this Item ?");
        alert.showAndWait().ifPresent(response ->{
            if (response==YES){
                Alert alert1;
                if(selectedIndex != -1){
                    ItemDTO selectedItem = allItems.get(selectedIndex);
                    ItemDTO itemById = itemBO.getItemById(selectedItem.getItemID());
                    Boolean isDeleted = itemBO.deleteItem(itemById.getItemID());
                    //Reload the table
                    setDataToTable();


                    if (isDeleted){
                          alert1 = new Alert(Alert.AlertType.INFORMATION, "Item Deleted !");
                        alert1.show();
                    }else {
                         alert1 = new Alert(Alert.AlertType.ERROR, "Item Not Deleted !");
                    }
                    alert1.show();


                }else{
                    Alert alert2 = new Alert(Alert.AlertType.ERROR,"Please select item first.");
                    alert2.show();
                }
            }
        });





    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        if(!isEdit){
            ItemDTO itemDTO = new ItemDTO(
                    txtItemId.getText(),
                    txtItemName.getText(),
                    txtBatchNumber.getText(),
                    Double.parseDouble(txtItemPrice.getText()),
                    Double.parseDouble(txtQtyOnHand.getText()),
                    txtSupplier.getText(),
                    Date.valueOf(LocalDate.now())
            );

            boolean b = itemBO.saveItem(itemDTO);

            if(b){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save Item ");
                alert.setHeaderText("Item Saved !");
                alert.show();

                // Clear Fields
                clearFields();

                // Generate Next ItemID
                generateAndSetItemId();

                //Reload the table
                setDataToTable();
            }
        }else {
            // Item Update method
            boolean updateResult = itemBO.updateItem(new ItemDTO(
                    txtItemId.getText(),
                    txtItemName.getText(),
                    txtBatchNumber.getText(),
                    Double.parseDouble(txtItemPrice.getText()),
                    Double.parseDouble(txtQtyOnHand.getText()),
                    txtSupplier.getText(),
                    Date.valueOf(LocalDate.now())
            ));

            if(updateResult){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Update");
                alert.setHeaderText("Item Successfully Updated !");
                alert.show();

                clearFields();

                initialize();

                // Restoring ADD button
                btnSave.setText("ADD");
                btnSave.setStyle("-fx-background-color:  #1abc9c");
                isEdit = false;
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Item Update");
                alert.setHeaderText("Item Not Updated !");
                alert.show();
            }
        }
    }

    private void clearFields() {
        txtBatchNumber.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtSupplier.clear();
        txtQtyOnHand.clear();
        datePicker.getEditor().clear();
    }

    private void setDataToTable() {
        allItems = itemBO.getAllItem();
        itemsTbl.setItems(allItems);
    }







}
