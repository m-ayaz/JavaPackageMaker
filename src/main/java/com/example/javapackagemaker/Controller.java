package com.example.javapackagemaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jarExtension = new FileChooser.ExtensionFilter("JAR Files (*.jar)", "*.jar");
    FileChooser.ExtensionFilter icoExtension = new FileChooser.ExtensionFilter("Icon Files (*.ico)", "*.ico");
    FileChooser.ExtensionFilter txtExtension = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
    File jarFile;
    String icoFilePath;
    String licenseFilePath;
    String savePath;
    String command;
    DirectoryChooser directoryChooser = new DirectoryChooser();
    @FXML
    private TextField appDescriptionArea;
    @FXML
    private TextField appNameArea;
    @FXML
    private TextArea terminalCodeArea;
    @FXML
    private Button browseJARFileButton;
    @FXML
    private ComboBox<String> appExtensionSelector;
    @FXML
    private Button createButton;
    @FXML
    private Pane mainPane;
    @FXML
    private TextField appVersionArea;
    @FXML
    private CheckBox winPerUserInstall;
    @FXML
    private CheckBox winDirChooser;
    @FXML
    private CheckBox winMenu;
    @FXML
    private CheckBox winShortcut;
    @FXML
    private Button browseOutputAppPathButton;
    @FXML
    private Button browseIconFileButton;
    @FXML
    private Button browseLicenseFileButton;
    @FXML
    private Label iconFilePathLabel;
    @FXML
    private Label jarFilePathLabel;
    @FXML
    private Label licenseFilePathLabel;
    @FXML
    private Label outputPathLabel;

    @FXML
    void browseIconFileButtonOnAction(ActionEvent event) {
        fileChooser.getExtensionFilters().setAll(icoExtension);
        try {
            icoFilePath = fileChooser.showOpenDialog(new ContextMenu()).getAbsolutePath();
            iconFilePathLabel.setText(icoFilePath);
            update();
        } catch (Exception ignored) {
        }
    }

    @FXML
    void browseJARFileButtonOnAction(ActionEvent event) {
        fileChooser.getExtensionFilters().setAll(jarExtension);
        try {
            jarFile = fileChooser.showOpenDialog(new ContextMenu());
            jarFilePathLabel.setText(jarFile.getAbsolutePath());
            update();
        } catch (Exception ignored) {
        }
    }

    @FXML
    void browseLicenseFileButtonOnAction(ActionEvent event) {
        fileChooser.getExtensionFilters().setAll(txtExtension);
        try {
            licenseFilePath = fileChooser.showOpenDialog(new ContextMenu()).getAbsolutePath();
            licenseFilePathLabel.setText(licenseFilePath);
            update();
//            System.out.println("asl;asl");
        } catch (Exception ignored) {
        }
    }

    @FXML
    void browseOutputAppPathButtonOnAction(ActionEvent event) {
        try {
            savePath = directoryChooser.showDialog(new ContextMenu()).getPath();
            outputPathLabel.setText(savePath);
            update();
        } catch (Exception ignored) {

        }
    }

    @FXML
    void createButtonOnAction(ActionEvent event) {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        processBuilder.redirectErrorStream(true);
        Process p;
        try {
            p = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

    void update() {
        if (jarFile != null) {
            command = "jpackage" +
                    " --input %s".formatted(jarFile.getParent()) +
                    " --main-jar %s".formatted(jarFile.getName()) +
//                    " --type deb".formatted(appExtensionSelector.getValue()) +
                    " --type %s".formatted(appExtensionSelector.getValue()) +
                    " --name %s".formatted(appNameArea.getText().equals("") ? appNameArea.getPromptText() : appNameArea.getText()) +
                    " --dest %s".formatted(savePath == null ? jarFile.getParent() : savePath) +
                    " --app-version %s".formatted(appVersionArea.getText().equals("") ? appVersionArea.getPromptText() : appVersionArea.getText()) +
                    (winDirChooser.isSelected() ? " --win-dir-chooser" : "") +
                    (winMenu.isSelected() ? " --win-menu" : "") +
                    (winPerUserInstall.isSelected() ? " --win-per-user-install" : "") +
                    (winShortcut.isSelected() ? " --win-shortcut" : "") +
                    (licenseFilePath == null ? "" : " --license-file " + licenseFilePath) +
                    " --win-help-url https://varzesh3.com" +
                    " --win-shortcut-prompt" +
                    " --win-update-url https://varzesh3.com" +
                    " --about-url https://varzesh3.com" +

//                    " --win-console"+
                    (icoFilePath == null ? "" : " --icon " + icoFilePath) +

//                " --win-help-url https://github.com".formatted() +
//                " --win-shortcut-prompt".formatted() +
                    " --verbose";

            System.out.println(command);


            terminalCodeArea.setText(command);
        }

    }

    @FXML
    private void initialize() {
//        pathToJarArea.setText("asdasda");
//        mainPane
//        pathToJarArea.setPromptText("<< current path >>");
//        pathToOutputArea.setPromptText("<< current path >>");
        appDescriptionArea.setPromptText("Created by JavaPackageMaker!");
        appNameArea.setPromptText("CoolApp");
        appVersionArea.setPromptText("1.0.0.0");
//        jarNameArea.setPromptText("cool-app");


//        winDirChooser.selectedProperty().addListener();


//        winDirChooser.setPrefSize(100,100);
//        winDirChooser.setMinSize(100,100);
//        winDirChooser.setMaxSize(100,100);

//        winDirChooser.setSelected();

        appExtensionSelector.getItems().add("exe");
        appExtensionSelector.getItems().add("msi");
        appExtensionSelector.setValue("exe");


        winDirChooser.selectedProperty().addListener((a, b, c) -> update());
        winMenu.selectedProperty().addListener((a, b, c) -> update());
        winShortcut.selectedProperty().addListener((a, b, c) -> update());
        winPerUserInstall.selectedProperty().addListener((a, b, c) -> update());

        appNameArea.textProperty().addListener((a, b, c) -> update());
//        pathToOutputArea.textProperty().addListener((a,b,c)->update());
        appDescriptionArea.textProperty().addListener((a, b, c) -> update());
        appVersionArea.textProperty().addListener((a, b, c) -> update());

        appExtensionSelector.valueProperty().addListener((a, b, c) -> update());


    }


}