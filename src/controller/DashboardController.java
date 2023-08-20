package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashboardController {

    public AnchorPane btnPay;
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
    public Label LblTotal;
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

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);
    ItemBo itemBo= (ItemBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.ITEM);

    public void btnPayOnAction(MouseEvent mouseEvent) {
    }

    public void btnClearOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnItemRemoveOnAction(ActionEvent actionEvent) {
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
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
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
}
