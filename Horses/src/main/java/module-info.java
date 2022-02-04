module com.example.horses {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.horses to javafx.fxml;
    exports com.example.horses;
    exports com.example.horses.Dataverwerking;
    opens com.example.horses.Dataverwerking to javafx.fxml;
}