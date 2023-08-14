package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFromContoller {
    public AnchorPane loginFromAnchorPane;
    public TextField userNameTxt;
    public JFXButton loginBtn;
    public PasswordField passwordTxt;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/MenuBar.fxml"));
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        System.out.println("Login on action call !");
        Stage loginStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        loginStage.close();
    }
}
