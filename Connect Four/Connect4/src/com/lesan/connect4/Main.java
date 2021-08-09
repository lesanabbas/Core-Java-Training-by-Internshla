package com.lesan.connect4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();
        controller.createPlayGround();

        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().addAll(menuBar);

        Scene scene = new Scene(rootGridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect4");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar createMenu() {
        Menu filemenu = new Menu("File");
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(actionEvent -> {
            resetGame();
        });

        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(actionEvent -> {
            resetGame();
        });

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(actionEvent -> exitGame());

        filemenu.getItems().addAll(newGame,resetGame,separatorMenuItem,exitGame);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutConnect4 = new MenuItem("About Connect4");
        aboutConnect4.setOnAction(actionEvent -> {
            aboutConnect4();
        });

        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(actionEvent -> {
            aboutMe();
        });

        helpMenu.getItems().addAll(aboutConnect4,aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu,helpMenu);

        return menuBar;
    }

    private void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    private void aboutMe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Lesan Abbas");
        alert.setContentText("Hi! I am Lesan ,a web developer focused on crafting great\n" +
                " web/app. Designing and coding have been my passion since\n" +
                " the day I started working on computer but I found myself in\n" +
                " to coding and developing amazing web app.\n\n" +
                "\t Here is a Temperature Converter App.");
        alert.show();
    }

    private void aboutConnect4() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect 4");
        alert.setHeaderText("How to play");
        alert.setContentText("\tConnect Four is a two-player connection game in which\n" +
                " the players first choose a color and then take turns dropping\n" +
                " colored discs from the top into a seven-column, six-row\n" +
                " vertically suspended grid. The pieces fall straight down,\n" +
                " occupying the next available space within the column. The\n" +
                " objective of the game is to be the first to form a horizontal,\n" +
                " vertical, or diagonal line of four of one's own discs.\n" +
                " \tConnect Four is a solved game. The first player can always\n" +
                " win by playing the right moves.");
        alert.show();
    }

    private void resetGame() {
//        TODO
    }


    public static void main(String[] args) {
        launch(args);
    }
}
