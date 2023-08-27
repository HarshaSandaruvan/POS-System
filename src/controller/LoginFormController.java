package controller;

import bo.BOFactory;
import bo.custom.LoginBo;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ObjectPasser;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginFromAnchorPane;
    public TextField userNameTxt;
    public JFXButton loginBtn;
    public PasswordField passwordTxt;
    public MenuBarController menuBarController;
    LoginBo loginBo = (LoginBo) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.LOGIN);
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String username=userNameTxt.getText();
        String password= passwordTxt.getText();

        if(fieldValidation()){

            boolean result = loginBo.checkPassword(username,password);

            ObjectPasser.userFullName=loginBo.getFullNameByUserName(username);

            if (Boolean.TRUE.equals(result)){
                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/MenuBar.fxml"));
                Scene scene=new Scene(parent);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Dashboard");
                stage.show();

                Stage loginStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                loginStage.close();
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Alert");
                alert.setHeaderText("Please check your User Name and Password !");
                alert.showAndWait();


            }


        }

    }
    private boolean fieldValidation(){
        if(userNameTxt.getText().isEmpty() && passwordTxt.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Please Enter User Name and Password !");
            alert.showAndWait();
            return false;
        }else {
            return true;
        }
    }

}
