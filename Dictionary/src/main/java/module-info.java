module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires freetts;
    requires java.sql;
    requires jlayer;

    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}