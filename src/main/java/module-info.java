module com.example.javapackagemaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javapackagemaker to javafx.fxml;
    exports com.example.javapackagemaker;
}