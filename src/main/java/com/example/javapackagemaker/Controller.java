package com.example.javapackagemaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

//    String jarFilePath;
//    String jarFileName;
//    String appName;
//    String appVersion;

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jarExtension = new FileChooser.ExtensionFilter("JAR Files (*.jar)", "*.jar");
    File jarFile;
    String command;
    @FXML
    private TextField appDescriptionArea;
    @FXML
    private TextField appNameArea;
    @FXML
    private TextField pathToModArea;
    @FXML
    private TextField pathToOutputArea;
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
    void browseJARFileButtonOnAction(ActionEvent event) {
//        mainCanvasItemsHandler.selectAll();
//        mainCanvasItemsHandler.removeSelectedObjectsFromMainCanvas();

        fileChooser.getExtensionFilters().add(jarExtension);
        try {
            jarFile = fileChooser.showOpenDialog(new ContextMenu());
//            System.out.println(file.getAbsolutePath());
//            System.out.println(file.getPath());
//            System.out.println(file.getAbsoluteFile());
//            System.out.println(file.getCanonicalFile());
//            System.out.println(file.getCanonicalPath());
//            jarFileName = jarFile.getName();
//            System.out.println(file.getParentFile());
//            jarFilePath = jarFile.getParent();
            update();
        } catch (Exception ignored) {

        }
//        JSONArray jsonArray = new JSONArray(Files.readString(Path.of(filePath)));
//        List<AppNode> appNodes=importProjectFromJSON(jsonArray);
//        appNodes.forEach(appNode -> mainCanvasItemsHandler.addToMainCanvas(appNode));
//        System.out.println("al;sla;sl;as");
    }

    @FXML
    void createButtonOnAction(ActionEvent event) {
//        appName = appNameArea.getText();
//        String desc = appDescArea.getText();
////        String jarPath = jarFilePath.getText();
////        String jarName = jarFileName.getText();
//        String outPath = pathToOutputArea.getText();
//        String modulePath = pathToModArea.getText();
//        String version=appVersionArea.getText();
//        String currentPath = Path.of("").toAbsolutePath().toString();
//        if (appName.equals("")) {
//            appName = "CoolApp";
//        }
//        if (desc.equals("")) {
//            desc = "Created by JavaPackageMaker!";
//        }
////        if (jarPath.equals("")) {
////            jarPath = currentPath;
////        }
////        if (jarName.equals("")) {
////            jarName = "cool-app";
////        }
//        if (outPath.equals("")) {
//            outPath = currentPath;
//        }
//        if (modulePath.equals("")) {
//            modulePath = currentPath;
//        }
//        if(version.equals("")){
//            version="1.0.0.0";
//        }
////        if(appVersionArea.equals("")){
////
////        }
//        desc = "\"" + desc + "\"";
////        jarName += ".jar";
//        String terminalCode = String.format("jpackage -t exe --name %s --description %s --app-version 1.0 --input %s --dest %s --main-jar %s --module-path %s --add-modules javafx.controls,javafx.media,javafx.fxml --win-shortcut --win-menu --win-dir-chooser --verbose", name, desc, outPath, modulePath);
//        terminalCodeArea.setWrapText(true);
//        terminalCodeArea.setText(terminalCode);
////        System.out.println("Text is "+pathToJarArea.getText());
//        System.out.println(Path.of("").toAbsolutePath().toString());
    }

    void update() {
//        System.out.println(jarFile);
        if(jarFile!=null) {
            command = "jpackage" +
                    " --input %s".formatted(jarFile.getParent()) +
                    " --main-jar %s".formatted(jarFile.getName()) +
                    " --type %s".formatted(appExtensionSelector.getValue()) +
                    " --name %s".formatted(appNameArea.getText().equals("")?appNameArea.getPromptText():appNameArea.getText()) +
                    " --dest %s".formatted(pathToOutputArea.getText().equals("")?jarFile.getParent():pathToOutputArea.getText()) +
                    " --app-version %s".formatted(appVersionArea.getText().equals("")?appVersionArea.getPromptText():appVersionArea.getText()) +
                    (winDirChooser.isSelected() ? " --win-dir-chooser" : "") +
                    (winShortcut.isSelected() ? " --win-shortcut" : "") +
                    (winPerUserInstall.isSelected() ? " --win-per-user-install" : "") +
                    (winMenu.isSelected() ? " --win-menu" : "") +
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
        pathToModArea.setPromptText("<< current path >>");
        pathToOutputArea.setPromptText("<< current path >>");
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











        winDirChooser.selectedProperty().addListener((a,b,c)->update());
        winMenu.selectedProperty().addListener((a,b,c)->update());
        winShortcut.selectedProperty().addListener((a,b,c)->update());
        winPerUserInstall.selectedProperty().addListener((a,b,c)->update());

        appNameArea.textProperty().addListener((a,b,c)->update());
        pathToOutputArea.textProperty().addListener((a,b,c)->update());
        appDescriptionArea.textProperty().addListener((a, b, c)->update());



    }


}