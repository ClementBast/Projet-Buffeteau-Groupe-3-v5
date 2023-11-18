module sio.projetbuffteauv3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sio.projetbuffteauv3 to javafx.fxml;
    opens sio.projetbuffteauv3.entities; // Ouvrez le package entities au module javafx.base

    exports sio.projetbuffteauv3;
}
