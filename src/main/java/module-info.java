module org.example.university2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.university2 to javafx.fxml;
    exports org.example.university2;
}