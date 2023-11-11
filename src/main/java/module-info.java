module sio.projetbuffteauv3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens sio.projetbuffteauv3 to javafx.fxml;
    exports sio.projetbuffteauv3;
}