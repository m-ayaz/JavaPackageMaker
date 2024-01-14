package com.example.javapackagemaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.nio.file.Path;

public class ControllerClass {

    @FXML
    private TextField appDescArea;

    @FXML
    private TextField appNameArea;

    @FXML
    private Button createButton;

    @FXML
    private TextField jarNameArea;

    @FXML
    private Pane mainPane;

    @FXML
    private TextField pathToJarArea;

    @FXML
    private TextField pathToModArea;

    @FXML
    private TextField pathToOutputArea;

    @FXML
    private TextArea terminalCodeArea;

    @FXML
    private void initialize(){
//        pathToJarArea.setText("asdasda");
//        mainPane
        pathToJarArea.setPromptText("<< current path >>");
        pathToModArea.setPromptText("<< current path >>");
        pathToOutputArea.setPromptText("<< current path >>");
        appDescArea.setPromptText("Created through JavaPackageMaker!");
        appNameArea.setPromptText("CoolApp");
        jarNameArea.setPromptText("cool-app");

    }

    @FXML
    void createButtonOnAction(ActionEvent event) {
        String name=appNameArea.getText();
        String desc=appDescArea.getText();
        String jarPath=pathToJarArea.getText();
        String jarName=jarNameArea.getText();
        String outPath=pathToOutputArea.getText();
        String modulePath=pathToModArea.getText();
        String currentPath=Path.of("").toAbsolutePath().toString();
        if(name.equals("")) {
            name = "CoolApp";
        }
        if(desc.equals("")) {
            desc = "Created through JavaPackageMaker!";
        }
        if(jarPath.equals("")) {
            jarPath = currentPath;
        }
        if(jarName.equals("")) {
            jarName = "cool-app";
        }
        if(outPath.equals("")) {
            outPath = currentPath;
        }
        if(modulePath.equals("")) {
            modulePath = currentPath;
        }
        desc="\""+desc+"\"";
        jarName+=".jar";
        String terminalCode=String.format("jpackage -t exe --name %s --description %s --app-version 1.0 --input %s --dest %s --main-jar %s --module-path %s --add-modules javafx.controls,javafx.media,javafx.fxml --win-shortcut --win-menu --win-dir-chooser --verbose",name,desc,jarPath,outPath,jarName,modulePath);
        terminalCodeArea.setWrapText(true);
        terminalCodeArea.setText(terminalCode);
//        System.out.println("Text is "+pathToJarArea.getText());
//        System.out.println(Path.of("").toAbsolutePath().toString());
    }

}
