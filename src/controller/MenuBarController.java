package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MenuBarController {
    public JFXButton menuDashboardBtn;

    public AnchorPane playGroundAnchorPane;
    public Label userNameLbl;
    public MenuButton menuOrdersBtn;
    public MenuButton menuCustomersBtn;
    public MenuButton menuItemsBtn;
    public Label dateLbl;
    public Label timeLbl;
    public JFXButton menuLogoutBtn;
    public LocalTime currentTime;
    public MenuItem BtnItemManager;


    public void initialize(){
        startClock();

    }
    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            currentTime = LocalTime.now();
            timeLbl.setText(currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
        }), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void itemMangerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/ManageItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        playGroundAnchorPane.getChildren().clear();
        playGroundAnchorPane.getChildren().add(load);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        ButtonType YES=new ButtonType("Yes");
        ButtonType NO=new ButtonType("No");


        Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"",YES,NO);
        alert.setHeaderText("Do you want Exit ?");
        alert.showAndWait().ifPresent(response ->{
            if (response==YES){
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
                    Scene scene=new Scene(parent);
                    Stage stage=new Stage();
                    stage.setScene(scene);

                    stage.setTitle("Login From");
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                Stage dashboardStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                dashboardStage.close();
            }else {
                System.out.println("No");
            }
        });


    }

    public void manageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/ManageCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        playGroundAnchorPane.getChildren().clear();
        playGroundAnchorPane.getChildren().add(load);
    }
}
