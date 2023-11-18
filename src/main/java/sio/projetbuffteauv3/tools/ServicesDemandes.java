package sio.projetbuffteauv3.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.projetbuffteauv3.entities.Demande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesDemandes {
    private Connection uneCnx ;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesDemandes(){
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<Demande> GetAllDemandes() throws SQLException{
        ObservableList<Demande> lesDemandes = FXCollections.observableArrayList();

        PreparedStatement ps = uneCnx.prepareStatement(
                "SELECT matiere.designation, demande.sous_matiere, demande.id, demande.date_updated\n" +
                        "FROM demande\n" +
                        "INNER JOIN matiere ON demande.id_matiere = matiere.id\n" +
                        "GROUP BY matiere.designation, demande.date_updated, demande.id;");


        rs = ps.executeQuery();
        //parcourir le jeu d'enregistrement
        while(rs.next())
        {
            // System.out.println("Designation: " + rs.getString(1));
            // System.out.println("Sous Matiere: " + rs.getString(2));
            // System.out.println("ID: " + rs.getInt(3));
            //System.out.println("Date Updated: " + rs.getString(4));
             Demande laDemande = new Demande(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
            lesDemandes.add(laDemande);
        }
        return lesDemandes;
    }
}
