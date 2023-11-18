package sio.projetbuffteauv3.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import sio.projetbuffteauv3.entities.Competence;
import sio.projetbuffteauv3.entities.Demande;
import sio.projetbuffteauv3.entities.Matiere;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServicesCompetences implements Initializable {
    private Connection uneCnx ;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesCompetences(){uneCnx = ConnexionBDD.getCnx();}
    public void initialize(URL url, ResourceBundle resourceBundle) {this.uneCnx = ConnexionBDD.getCnx();}
    public ObservableList<Competence> GetMatiereCompetences(int id_user) throws SQLException {
        ObservableList<Competence> lesMatCompetences = FXCollections.observableArrayList();
        PreparedStatement ps = uneCnx.prepareStatement("SELECT matiere.designation, competence.sous_matiere FROM competence JOIN matiere ON matiere.id = competence.id_matiere WHERE id_user = ?");
        ps.setInt(1, id_user);

        rs = ps.executeQuery();

        while(rs.next()) {
            Competence uneCompetence = new Competence(rs.getString(1), rs.getString(2), id_user);
            lesMatCompetences.add(uneCompetence);
        }
        return lesMatCompetences;
    }

    public ObservableList<Competence> GetSousMatiereCompetences(int id_user, String matiere) throws SQLException {
        ObservableList<Competence> lesSousMatCompetences = FXCollections.observableArrayList();
        PreparedStatement ps = uneCnx.prepareStatement("SELECT competence.sous_matiere FROM competence JOIN matiere ON matiere.id = competence.id_matiere \n" +
                "WHERE matiere.designation = ? AND id_user = ?;");
        ps.setString(1, matiere);
        ps.setInt(2, id_user);
        rs = ps.executeQuery();

        while(rs.next()) {

            ObservableList lesCompetences = FXCollections.observableArrayList();
            String[] compSousMat = rs.getString(1).split("#");

            for (String compSousMats : compSousMat) {
                if (!compSousMats.isEmpty()) {
                    Competence uneCompetence = new Competence(matiere, compSousMats, id_user);

            lesSousMatCompetences.add(uneCompetence);
        }
            }
        }
        return lesSousMatCompetences;

}
    public void insererCompetence(String matiere, int idUtilisateur, String sousMat) throws SQLException {
        ps = uneCnx.prepareStatement("INSERT INTO competence (id_matiere, id_user, sous_matiere, statut) VALUES ((SELECT id FROM matiere WHERE designation = ?), ?, ?, 1)");
        ps.setString(1, matiere);
        ps.setInt(2, idUtilisateur);
        ps.setString(3, sousMat);
        ps.executeUpdate();
    }
    public void supprimerCompetence(String matiere, int idUtilisateur) throws SQLException {

        ps = uneCnx.prepareStatement("DELETE FROM competence WHERE id_matiere = (SELECT id FROM matiere WHERE designation = ?) AND id_user = ?");
        ps.setString(1, matiere);
        ps.setInt(2, idUtilisateur);
        ps.executeUpdate();
    }



}