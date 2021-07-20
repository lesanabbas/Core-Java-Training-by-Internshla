package com.lesan.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("initializing");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("start");
        VBox root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        MenuBar menuBar = createMenu();
        root.getChildren().add(0,menuBar);

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("assets/icon.png")));
        primaryStage.setTitle("Temperature Converter Tool");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private MenuBar createMenu() {
        Menu filemenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");

        newMenuItem.setOnAction(actionEvent -> System.out.println("Clicked on New Menu Item"));

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem quitMenuItem = new MenuItem("Quit");

        quitMenuItem.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });

        filemenu.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutApp = new MenuItem("About");

        aboutApp.setOnAction(actionEvent -> aboutApp());

        helpMenu.getItems().addAll(aboutApp);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu,helpMenu);

        return menuBar;
    }

    private void aboutApp() {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("My First Alert");
        alertDialog.setHeaderText("Learning JavaFx");
        alertDialog.setContentText("Hi! I am Lesan ,a web developer focused on crafting great\n" +
                " web/app. Designing and coding have been my passion since\n" +
                " the day I started working on computer but I found myself in\n" +
                " to coding and developing amazing web app.\n\n" +
                "\t Here is a Temperature Converter App.");

        ButtonType yesBtn = new ButtonType("Yes");
        ButtonType noBtn = new ButtonType("No");
        alertDialog.getButtonTypes().setAll(yesBtn,noBtn);

        Optional<ButtonType> clickBtn = alertDialog.showAndWait();

        if(clickBtn.isPresent() && clickBtn.get() == yesBtn) {
            System.out.println("Yes Clicked");
        } else {
            System.out.println("No Clicked");
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        super.stop();
    }

    public static void main(String[] args) {
        System.out.println("Main");
        launch(args);
    }
}
