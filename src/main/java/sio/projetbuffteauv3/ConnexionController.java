package sio.projetbuffteauv3;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetbuffteauv3.entities.Utilisateur;
import sio.projetbuffteauv3.tools.ConnexionBDD;
import sio.projetbuffteauv3.tools.ServiceConnexion;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {
    ConnexionBDD maCnx;
    ServiceConnexion serviceConnexion;
    Utilisateur connexUtilisateur;
    Utilisateur verifUtilisateur;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMdp;
    @FXML
    private Button btnConnexion;
    @FXML
    private AnchorPane apConnexion;
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            maCnx = new ConnexionBDD();
            serviceConnexion = new ServiceConnexion();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onBtnConnexionClicked(Event event) throws SQLException, IOException {

            String unEmail = txtEmail.getText();
            String unMdp = txtMdp.getText();

            if (serviceConnexion.verifEmailMdpUtilisateur(unEmail, unMdp) == 1) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("etudiant-view.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();

    }
    }
}

