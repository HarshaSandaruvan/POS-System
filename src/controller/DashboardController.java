package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import dto.CustomerDTO;
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
    public Label LblSupplier;
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

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.CUSTOMER);


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
}
