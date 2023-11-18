package sio.projetbuffteauv3;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import sio.projetbuffteauv3.entities.Demande;
import sio.projetbuffteauv3.tools.ConnexionBDD;
import sio.projetbuffteauv3.tools.ServicesDemandes;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {

    ConnexionBDD maCnx;
    ServicesDemandes servicesDemandes = new ServicesDemandes();

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
    private ComboBox cboCompSousMat;
    @javafx.fxml.FXML
    private ComboBox cboCompMat;
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
    private ComboBox cboSousMatModifDem;
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

    @javafx.fxml.FXML
    public void btnCompClicked(Event event) {
        apComp.toFront();
    }

    @javafx.fxml.FXML
    public void btnDemandesClicked(Event event) {
        apDemandes.toFront();
    }

    @javafx.fxml.FXML
    public void btnMesAidesClicked(Event event) {
        apMesAides.toFront();
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
    public void btnCreerCompClicked(Event event) {
        apCreerComp.toFront();
    }

    @javafx.fxml.FXML
    public void btnModifierCompClicked(Event event) {
        apModifierComp.toFront();
    }

    @javafx.fxml.FXML
    public void btnCreerCompValiderClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnModifierCompValiderClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnCreerDemClicked(Event event) {
        apCreerDem.toFront();
    }

    @javafx.fxml.FXML
    public void btnModifierDemClicked(Event event) {
        apModifDem.toFront();
    }

    @javafx.fxml.FXML
    public void btnValiderDemClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnValiderModifDemClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnValiderLesAidesClicked(Event event) {
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            maCnx = new ConnexionBDD();
            tcLesAidesMat.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("matiere"));
            tcLesAidesSousMat.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("sousmatiere"));
            tcLesAidesId.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("id"));
            tcLesAidesDateFin.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("date"));
            ServicesDemandes servicesDemandes = new ServicesDemandes();
            tvLesAides.setItems(servicesDemandes.GetAllDemandes());
        }
     catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
}


