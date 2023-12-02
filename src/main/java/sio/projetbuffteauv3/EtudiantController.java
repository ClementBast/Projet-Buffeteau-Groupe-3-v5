package sio.projetbuffteauv3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;

import sio.projetbuffteauv3.entities.Competence;
import sio.projetbuffteauv3.entities.Demande;
import sio.projetbuffteauv3.entities.Matiere;
import sio.projetbuffteauv3.entities.Utilisateur;
import sio.projetbuffteauv3.tools.*;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {

    public static Utilisateur setUtilisateur;
    ConnexionBDD maCnx;



    @javafx.fxml.FXML
    private Button btnComp;
    @javafx.fxml.FXML
    private Button btnDemandes;
    @javafx.fxml.FXML
    private Button btnMesAides;
    @javafx.fxml.FXML
    private Button btnLesAides;
    @javafx.fxml.FXML
    private Button btnStats;
    @javafx.fxml.FXML
    private AnchorPane apComp;
    @javafx.fxml.FXML
    private TableView tvCompMatières;
    @javafx.fxml.FXML
    private TableColumn tcCompMat;
    @javafx.fxml.FXML
    private TableView tvCompSousMat;
    @javafx.fxml.FXML
    private TableColumn tcCompSousMat;
    @javafx.fxml.FXML
    private Button btnCreerComp;
    @javafx.fxml.FXML
    private Button btnModifierComp;
    @javafx.fxml.FXML
    private AnchorPane apCreerComp;
    @javafx.fxml.FXML
    private Button btnCreerCompValider;
    @javafx.fxml.FXML
    private AnchorPane apModifierComp;
    @javafx.fxml.FXML
    private ComboBox cboModifSousMat;
    @javafx.fxml.FXML
    private TextField txtModifCompMat;
    @javafx.fxml.FXML
    private Button btnModifierCompValider;
    @javafx.fxml.FXML
    private AnchorPane apDemandes;
    @javafx.fxml.FXML
    private TableView tvDemMatières;
    @javafx.fxml.FXML
    private TableColumn tcDemMat;
    @javafx.fxml.FXML
    private TableView tvDemSousMat;
    @javafx.fxml.FXML
    private TableColumn tcDemSousMat;
    @javafx.fxml.FXML
    private Button btnCreerDem;
    @javafx.fxml.FXML
    private Button btnModifierDem;
    @javafx.fxml.FXML
    private AnchorPane apCreerDem;
    @javafx.fxml.FXML
    private DatePicker dpCreerDemDate;
    @javafx.fxml.FXML
    private TableView tvCreerDemMat;
    @javafx.fxml.FXML
    private TableColumn tcCreerDemMat;
    @javafx.fxml.FXML
    private TableView tvDemCreerSousMat;
    @javafx.fxml.FXML
    private TableColumn tcCreerDemSousMat;
    @javafx.fxml.FXML
    private Button btnCreerValiderDem;
    @javafx.fxml.FXML
    private AnchorPane apModifDem;
    @javafx.fxml.FXML
    private TextField txtMatModifDem;
    @javafx.fxml.FXML
    private DatePicker dpDateModifDem;
    @javafx.fxml.FXML
    private Button btnValiderModifDem;
    @javafx.fxml.FXML
    private AnchorPane apMesAides;
    @javafx.fxml.FXML
    private TableView tvMesAides;
    @javafx.fxml.FXML
    private TableColumn tcMesAidesMat;
    @javafx.fxml.FXML
    private TableColumn tcMesAidesSousMat;
    @javafx.fxml.FXML
    private TableColumn tcMesAidesId;
    @javafx.fxml.FXML
    private TableColumn tcMesAidesDateFin;
    @javafx.fxml.FXML
    private AnchorPane apLesAides;
    @javafx.fxml.FXML
    private TableView tvLesAides;
    @javafx.fxml.FXML
    private TableColumn tcLesAidesMat;
    @javafx.fxml.FXML
    private TableColumn tcLesAidesSousMat;
    @javafx.fxml.FXML
    private TableColumn tcLesAidesId;
    @javafx.fxml.FXML
    private TableColumn tcLesAidesDateFin;
    @javafx.fxml.FXML
    private Button btnValiderLesAides;
    @javafx.fxml.FXML
    private AnchorPane apStatsEtudiant;
    @javafx.fxml.FXML
    private TreeView tvStatsEtudfiant;
    ServicesDemandes servicesDemandes = new ServicesDemandes();
    ServiceConnexion serviceConnexion = new ServiceConnexion();
    ServicesCompetences servicesCompetences = new ServicesCompetences();
    ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
    ServicesMatieres servicesMatieres = new ServicesMatieres();
    ServiceSoutien serviceSoutien = new ServiceSoutien();

    Utilisateur unUtilisateur;
    @javafx.fxml.FXML
    private TableView <Matiere>tvCreerCompSousMat;
    @javafx.fxml.FXML
    private TableColumn tcCreerCompSousMat;
    @javafx.fxml.FXML
    private TableView <Matiere>tvCreerCompMat;
    @javafx.fxml.FXML
    private TableColumn tcCreerCompMat;
    @javafx.fxml.FXML
    private ListView lvSousMatModifDem;

    public void initDatas (Utilisateur c)
    {
        unUtilisateur = c;
    }

    @javafx.fxml.FXML
    public void btnCompClicked(Event event) throws SQLException {

        apComp.toFront();

        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

        tcCompMat.setCellValueFactory(new PropertyValueFactory<>("matiereComp"));
        tvCompMatières.setItems(servicesCompetences.GetMatiereCompetences(idUser));

    }
    @javafx.fxml.FXML
    public void tvCompMatClicked(Event event) throws SQLException {
        Competence compSelec = (Competence) tvCompMatières.getSelectionModel().getSelectedItem();
        String matiere = compSelec.getMatiereComp();
        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

       tcCompSousMat.setCellValueFactory(new PropertyValueFactory<>("sousMatiereComp"));
       tvCompSousMat.setItems(servicesCompetences.GetSousMatiereCompetences(idUser, matiere));

        System.out.println(tvCompMatières.getSelectionModel().getSelectedItem().toString());
    }

    @javafx.fxml.FXML
    public void btnDemandesClicked(Event event) throws SQLException {

        apDemandes.toFront();
        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

        tcDemMat.setCellValueFactory(new PropertyValueFactory<>("matiereDem"));
        tvDemMatières.setItems(servicesDemandes.GetMatiereDemandes(idUser));
    }

    @javafx.fxml.FXML
    public void tvDemMatClicked(Event event) throws SQLException{

        String matiere = ((Demande) tvDemMatières.getSelectionModel().getSelectedItem()).getMatiereDem();

        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

        ObservableList<Demande> lesSousMatieres = FXCollections.observableArrayList(servicesDemandes.GetSousMatiereDemandes(idUser, matiere));


        tcDemSousMat.setCellValueFactory(new PropertyValueFactory<>("sousMatiereDem"));
        tvDemSousMat.setItems(lesSousMatieres);

        System.out.println(lesSousMatieres);
    }

    @javafx.fxml.FXML
    public void btnMesAidesClicked(Event event) throws SQLException {
        apMesAides.toFront();

        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

        tcMesAidesMat.setCellValueFactory(new PropertyValueFactory<>("matiereDem"));
        tcMesAidesSousMat.setCellValueFactory(new PropertyValueFactory<>("sousMatiereDem"));
        tcMesAidesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcMesAidesDateFin.setCellValueFactory(new PropertyValueFactory<>("date"));

        tvMesAides.setItems(servicesDemandes.GetAllDemandesByIdUser(idUser));
    }

    @javafx.fxml.FXML
    public void btnLesAidesClicked(Event event) {
        apLesAides.toFront();
    }

    @javafx.fxml.FXML
    public void btnStatsClicked(Event event) {
        apStatsEtudiant.toFront();
    }

    @javafx.fxml.FXML
    public void btnCreerCompClicked(Event event) throws SQLException
    {
        apCreerComp.toFront();
        ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList(servicesMatieres.getAllMatieres());
        tcCreerCompMat.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        tvCreerCompMat.setItems(lesMatieres);
    }

    @javafx.fxml.FXML
    public void tvCreerCompMatClicked(Event event) throws SQLException {

        String matiereSelec = ((Matiere)tvCreerCompMat.getSelectionModel().getSelectedItem()).getMatiere();


        ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList(servicesMatieres.getAllSousMatieresByMatieres(matiereSelec));

        System.out.println(matiereSelec);
        for (Matiere sousMatiere : lesSousMatieres) {
            System.out.println(sousMatiere.getSousMatiere());
        }

        System.out.println(lesSousMatieres);
        tcCreerCompSousMat.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
        tvCreerCompSousMat.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvCreerCompSousMat.setItems(lesSousMatieres);

    }
    @javafx.fxml.FXML
    public void btnCreerCompValiderClicked(Event event) throws SQLException {

            ServicesCompetences servicesCompetences = new ServicesCompetences();
            String matiereSelec = ((Matiere)tvCreerCompMat.getSelectionModel().getSelectedItem()).getMatiere();
            int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

            ObservableList<Matiere> lesSousMatieres = tvCreerCompSousMat.getSelectionModel().getSelectedItems();
            String listeSousMat = "";

            for (Matiere sousMatiere : lesSousMatieres) {
                listeSousMat += "#" + sousMatiere.getSousMatiere();
            }
            servicesCompetences.supprimerCompetence(matiereSelec, idUser);
            servicesCompetences.insererCompetence(matiereSelec, idUser, listeSousMat);
        }

        @javafx.fxml.FXML
    public void btnModifierCompClicked(Event event) {
        apModifierComp.toFront();
    }



    @javafx.fxml.FXML
    public void btnModifierCompValiderClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnCreerDemClicked(Event event) throws SQLException {
        apCreerDem.toFront();
        ObservableList<Matiere> lesMat = FXCollections.observableArrayList(servicesMatieres.getAllMatieres());
        tcCreerDemMat.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        tvCreerDemMat.setItems(lesMat);
    }

    public void btnModifierDemClicked(Event event) throws SQLException {
        apModifDem.toFront();

        if (tvDemMatières.getSelectionModel().getSelectedItem() != null) {
            Demande demandeSelec = (Demande) tvDemMatières.getSelectionModel().getSelectedItem();
            System.out.println("Matière sélectionnée : " + demandeSelec.getMatiereDem());

            String matiereSelec = demandeSelec.getMatiereDem();
            txtMatModifDem.setText(matiereSelec);

            ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList(servicesMatieres.getAllSousMatieresByMatieres(matiereSelec));

            lvSousMatModifDem.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            lvSousMatModifDem.setItems(lesSousMatieres);
            lvSousMatModifDem.getSelectionModel().selectFirst();
        } else {
            System.out.println("Aucune matière sélectionnée");
        }
    }

    @javafx.fxml.FXML
    public void btnValiderDemClicked(Event event) throws SQLException {
        ServicesDemandes servicesDemandes = new ServicesDemandes();
        String matiereSelec = ((Matiere) tvCreerDemMat.getSelectionModel().getSelectedItem()).getMatiere();
        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());
        LocalDate dateFinDemande = dpCreerDemDate.getValue();
        if (dateFinDemande == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir la date de fin de la demande.");
            alert.setHeaderText("");
            alert.showAndWait();
            return;
        } else if (matiereSelec == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir la matière de votre demande.");
            alert.setHeaderText("");
            alert.showAndWait();
            return;
        }
        Timestamp dateUpdated = Timestamp.valueOf(dateFinDemande.atStartOfDay());
        ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList(servicesMatieres.getAllSousMatieresByMatieres(matiereSelec));

        String listeSousMat = "";

        for (Matiere sousMatiere : lesSousMatieres) {
            listeSousMat += "#" + sousMatiere.getSousMatiere();
        }
        servicesDemandes.insererDemande(matiereSelec, idUser, listeSousMat, java.sql.Date.valueOf(dateFinDemande), new java.sql.Date(dateUpdated.getTime()));

    }

    @javafx.fxml.FXML
    public void btnValiderModifDemClicked(Event event) throws SQLException {

        int idUser = serviceUtilisateur.getIdUtilisateurByMail(unUtilisateur.getEmail());

        ServicesDemandes servicesDemandes = new ServicesDemandes();

        if (tvDemMatières.getSelectionModel().getSelectedItem() != null) {
            Demande demandeSelec = (Demande) tvDemMatières.getSelectionModel().getSelectedItem();


            System.out.println("Demande modifiée - Matière sélectionnée : " + demandeSelec.getMatiereDem());

            String matiereSelected = demandeSelec.getMatiereDem();

            ObservableList<Matiere> lesSousMatieres = lvSousMatModifDem.getSelectionModel().getSelectedItems();

            String listeSousMat = "";
            for (Matiere sousMatiere : lesSousMatieres) {
                listeSousMat += "#" + sousMatiere.getSousMatiere();
            }



            servicesDemandes.supprimerDemande(matiereSelected, idUser);
            servicesDemandes.insererDemande(matiereSelected,idUser, listeSousMat, java.sql.Date.valueOf(dpDateModifDem.getValue()), new java.sql.Date(System.currentTimeMillis()));

            System.out.println("Demande modifiée avec succès.");

        } else {
            System.out.println("Aucune demande sélectionnée");
        }
    }

    @javafx.fxml.FXML
    public void btnValiderLesAidesClicked(Event event) throws SQLException {
        Demande selectedDemande = (Demande) tvLesAides.getSelectionModel().getSelectedItem();
        //String matiereSelec = selectedDemande.getMatiereDem();
       // ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList(servicesMatieres.getAllSousMatieresByMatieres(matiereSelec));

       // String dateDem = selectedDemande.getDate();

       // int demandeId = selectedDemande.getId();

       //    servicesDemandes.insererDemande(demandeId, dateDem);


    }
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {

            maCnx = new ConnexionBDD();
            tcLesAidesMat.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("matiereDem"));
            tcLesAidesSousMat.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("sousMatiereDem"));
            tcLesAidesId.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("id"));
            tcLesAidesDateFin.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("date"));

            servicesDemandes = new ServicesDemandes();
            tvLesAides.setItems(servicesDemandes.GetAllDemandes());

        }
     catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @javafx.fxml.FXML
    public void tvCreerDemMatClicked(Event event) throws SQLException {
        String matiereSelec = ((Matiere)tvCreerDemMat.getSelectionModel().getSelectedItem()).getMatiere();
        ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList(servicesMatieres.getAllSousMatieresByMatieres(matiereSelec));
        System.out.println(matiereSelec);
        for (Matiere sousMatiere : lesSousMatieres) {
            System.out.println("les sous matieres :" + sousMatiere.getSousMatiere());
        }

        System.out.println(lesSousMatieres);
        tcCreerDemSousMat.setCellValueFactory(new PropertyValueFactory<>("sousMatiere"));
        tvDemCreerSousMat.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvDemCreerSousMat.setItems(lesSousMatieres);
    }
}


