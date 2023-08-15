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

public class AddItemFromController {
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

        setDataToTable();

        // Set listener to the table
        itemsTbl.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });
    }

    ItemBo itemBO = (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);
    public void clearBtnOnAction(ActionEvent actionEvent) {
        txtBatchNumber.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtSupplier.clear();
        txtQtyOnHand.clear();
        datePicker.getEditor().clear();
    }

    public void editBtnOnAction(ActionEvent actionEvent) {
    }



    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        ItemDTO itemDTO=new ItemDTO(
                txtItemId.getText(),
                txtItemName.getText(),
                txtBatchNumber.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Double.parseDouble(txtQtyOnHand.getText()),
                txtSupplier.getText(),
                Date.valueOf(LocalDate.now())
        );
        Boolean isSave = itemBO.saveItem(itemDTO);

        Alert alert;
        if (Boolean.TRUE.equals(isSave)){
             alert=new Alert(Alert.AlertType.INFORMATION,"Item Saved");

            //Clear Fields
             clearFields();

            //Generate Next Item Id
             generateAndSetItemId();

             //Reload the Table
             setDataToTable();

        }else {
             alert=new Alert(Alert.AlertType.ERROR,"Something Wrong");
        }
        alert.show();
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
