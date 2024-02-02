package com.example.javapackagemaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("JavaPackageDeploymentGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 687, 511);
        stage.setTitle("Java Package Maker for Windows");
//        stage.setResizable(false);
        stage.setScene(scene);

//        System.out.println(MainClass.class.getResourceAsStream("a.png"));

//        stage.getIcons().add(new Image(MainClass.class.getResourceAsStream("bb.bmp")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}