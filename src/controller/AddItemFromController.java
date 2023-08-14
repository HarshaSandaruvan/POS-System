package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddItemFromController {
    public AnchorPane addItemAnchorPane;
    public TextField txtItemName;
    public TextField txtBatchNumber;
    public TextField txtPrice;
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

    public void clearBtnOnAction(ActionEvent actionEvent) {
    }

    public void editBtnOnAction(ActionEvent actionEvent) {
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
    }
}
