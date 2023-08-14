package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
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
        URL resource = getClass().getResource("/view/AddItemFrom.fxml");
        Parent load = FXMLLoader.load(resource);
        playGroundAnchorPane.getChildren().clear();
        playGroundAnchorPane.getChildren().add(load);
    }
}
