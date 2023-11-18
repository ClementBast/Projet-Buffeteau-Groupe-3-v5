package sio.projetbuffteauv3.tools;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import sio.projetbuffteauv3.entities.Utilisateur;


public class ServiceConnexion implements Initializable {
    private Connection uneCnx ;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceConnexion(){
        uneCnx = ConnexionBDD.getCnx();
    }
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.uneCnx = ConnexionBDD.getCnx();
    }

    public int verifEmailMdpUtilisateur(String email, String motDePasse) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT * FROM `user` WHERE email = ? AND password = ?");
        ps.setString(1, email);
        ps.setString(2, motDePasse);
        rs = ps.executeQuery();
        int verif = 0;
        if (rs.next()) {
            verif = 1;
            System.out.println("Utilisateur trouvé");
            System.out.println("email :" + email);
            System.out.println("mdp :" + motDePasse);

        }
        else{System.out.println("Utilisateur introuvable");}
        return verif;
    }
    }

