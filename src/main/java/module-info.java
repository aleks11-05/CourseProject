module org.example.university2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;

    exports org.example.university2.View;

    opens org.example.university2 to javafx.fxml;
    exports org.example.university2;
    exports org.example.university2.Models;
    opens org.example.university2.Models to javafx.fxml;
    exports org.example.university2.Containers;
    opens org.example.university2.Containers to javafx.fxml;
}