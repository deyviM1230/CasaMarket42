module com.example.demostrategy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demostrategy to javafx.fxml;
    exports com.example.demostrategy;
}