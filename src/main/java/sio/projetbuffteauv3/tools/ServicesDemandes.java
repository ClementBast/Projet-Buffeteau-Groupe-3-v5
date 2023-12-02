package sio.projetbuffteauv3.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.projetbuffteauv3.entities.Competence;
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
        while(rs.next())
        {
             Demande laDemande = new Demande(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
            lesDemandes.add(laDemande);
        }
        return lesDemandes;
    }

    public ObservableList<Demande> GetAllDemandesByIdUser(int idUser) throws SQLException{
        ObservableList<Demande> lesDemandes = FXCollections.observableArrayList();

        PreparedStatement ps = uneCnx.prepareStatement(
                "SELECT matiere.designation, demande.sous_matiere, demande.id, demande.date_updated\n" +
                        "FROM demande\n" +
                        " JOIN matiere ON demande.id_matiere = matiere.id\n" +
                        "JOIN soutien ON demande.id = soutien.id_demande\n" +
                        "JOIN competence ON soutien.id_competence = competence.id\n" +
                        "WHERE competence.id_user = ?;");

        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        while(rs.next())
        {
            Demande laDemande = new Demande(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
            lesDemandes.add(laDemande);
        }
        return lesDemandes;
    }

    public ObservableList<Demande> GetMatiereDemandes(int id_user) throws SQLException {
        ObservableList<Demande> lesMatDemandes = FXCollections.observableArrayList();
        PreparedStatement ps = uneCnx.prepareStatement("SELECT DISTINCT matiere.designation, demande.sous_matiere, demande.id, demande.date_updated\n" +
                "FROM competence\n" +
                "JOIN matiere ON matiere.id = competence.id_matiere\n" +
                "JOIN demande ON demande.id_matiere = matiere.id\n" +
                "WHERE demande.id_user = ?;\n");
        ps.setInt(1, id_user);

        rs = ps.executeQuery();

        while (rs.next()) {
            Demande uneDemande = new Demande(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            lesMatDemandes.add(uneDemande);
        }
        return lesMatDemandes;
    }

    public ObservableList<Demande> GetSousMatiereDemandes(int id_user, String matiereDem) throws SQLException {
        ObservableList<Demande> lesSousMatDemandes = FXCollections.observableArrayList();
        PreparedStatement ps = uneCnx.prepareStatement("SELECT DISTINCT demande.sous_matiere\n" +
                "FROM demande\n" +
                "JOIN competence ON demande.id_matiere = competence.id_matiere\n" +
                "JOIN matiere ON matiere.id = demande.id_matiere\n" +
                "WHERE demande.id_user = ? AND matiere.designation = ?");
        ps.setInt(1, id_user);
        ps.setString(2, matiereDem);
        rs = ps.executeQuery();

        while(rs.next()) {
            ObservableList lesDemandes = FXCollections.observableArrayList();
            String[] demSousMat = rs.getString(1).split("#");

            for (String demSousMats : demSousMat) {
                if (!demSousMats.isEmpty()) {
                    Demande uneMatiere = new Demande(matiereDem, demSousMats, id_user, "");
                    lesSousMatDemandes.add(uneMatiere);
                }
            }
        }
        return lesSousMatDemandes;
    }
    private int getIdMatiereByDesignation(String matiereDesignation) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT id FROM matiere WHERE designation = ?");
        ps.setString(1, matiereDesignation);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Matière non trouvée pour la désignation: " + matiereDesignation);
        }
    }
    public void insererDemande(String matiere, int idUtilisateur, String sousMat, java.sql.Date dateFinDemande, java.sql.Date dateUpdated) throws SQLException {
        int idMatiere = getIdMatiereByDesignation(matiere);

        ps = uneCnx.prepareStatement("INSERT INTO demande (id_matiere, id_user, sous_matiere, status, date_updated, date_fin_demande) VALUES (?, ?, ?, 1, ?, ?)");
        ps.setInt(1, idMatiere);
        ps.setInt(2, idUtilisateur);
        ps.setString(3, sousMat);
        ps.setDate(4, dateUpdated);
        ps.setDate(5, dateFinDemande);
        ps.executeUpdate();
    }
    public void supprimerDemande(String matiere, int idUtilisateur) throws SQLException {
        int idMatiere = getIdMatiereByDesignation(matiere);
        System.out.println("ID de la matière pour " + matiere + " : " + idMatiere);

        ps = uneCnx.prepareStatement("DELETE FROM demande WHERE id_matiere = ? AND id_user = ?");
        ps.setInt(1, idMatiere);
        ps.setInt(2, idUtilisateur);
        ps.executeUpdate();
    }
}
