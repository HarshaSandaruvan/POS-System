package controller;

import bo.BOFactory;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
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

    ItemBo itemBO = (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);
    public void clearBtnOnAction(ActionEvent actionEvent) {
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
        }else {
             alert=new Alert(Alert.AlertType.ERROR,"Something Wrong");
        }
    }
}
