package views.controllers;

import Entities.Consultation;
import Entities.Medicament;
import Entities.Ordonnance;
import Entities.Users;
import Service.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FaireConsultationController implements Initializable {

    private ObservableList<String> obCode;
    private ObservableList<Medicament> obMedocs;
    Services service = new Services();

    @FXML
    private AnchorPane anchorDetails;

    @FXML
    private AnchorPane anchorMedicaments;

    @FXML
    private AnchorPane anchorRV;

    @FXML
    private ComboBox<String> cboCodePatient;

    @FXML
    private ComboBox<String> cboHeure;

    @FXML
    private ComboBox<Medicament> cboMedicaments;

    @FXML
    private ComboBox<String> cboPosologie;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField txtfAntecedants;

    @FXML
    private TextField txtfNom;

    @FXML
    private TextField txtfPrenom;

    @FXML
    private TextField txtfTelephone;

    @FXML
    void handlePlanifier(MouseEvent event) {
        LocalDate str = dpDate.getValue();
        Date datee = Date.valueOf(str);
        Time time = Time.valueOf(cboHeure.getSelectionModel().getSelectedItem());
        int idMedecin=ConnexionController.getCrtl().getUser().getId();
        Users user=service.findUserByCode(cboCodePatient.getValue());
        Consultation consultation = new Consultation(idMedecin,datee,time,"CONSULTATION",user.getCode());
        if(service.demanderConsultation(consultation) != 0){
            showAlert("Message","Votre rendez_vous a été bien enregistré", Alert.AlertType.CONFIRMATION);
        }else {
            showAlert("Message","Veuillez indiquer la date et l'heure du rendez_vous", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void handlePrescrire(MouseEvent event) {
        Ordonnance ordonnance = new Ordonnance(cboCodePatient.getValue(),service.SelectIdByCode(cboCodePatient.getValue()),ConnexionController.getCrtl().getUser().getId(),cboMedicaments.getValue().getCodeMedoc(),cboPosologie.getValue());
        int i = service.SaveOrdonnance(ordonnance);
        showAlert("Message","L'ordonnance a été bien ajoutée", Alert.AlertType.CONFIRMATION);
        cboMedicaments.valueProperty().set(null);
        cboPosologie.valueProperty().set(null);

    }


    @FXML
    void handleSearch(MouseEvent event) {
        anchorDetails.setVisible(true);
        anchorMedicaments.setVisible(true);
        anchorRV.setVisible(true);
        Users user = service.findUserByCode(cboCodePatient.getValue());
        txtfNom.setText(user.getNom());
        txtfPrenom.setText(user.getPrenom());
        txtfTelephone.setText(String.valueOf(user.getTel()));
        if(user.getAntecedantMedicaux() != null){
            txtfAntecedants.setText(user.getAntecedantMedicaux());
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxCodePatient(cboCodePatient);
        loadComboBoxPosologie();
        loadComboBoxMedicament(cboMedicaments);
        loadComboBoxHour();
    }

    public void loadComboBoxHour(){
        cboHeure.getItems().addAll("8:00:00","9:00:00","10:00:00","11:00:00","12:00:00","15:00:00","16:00:00","17:00:00","18:00:00");
    }

    public void loadComboBoxMedicament(ComboBox<Medicament> cbo){
        obMedocs = FXCollections.observableArrayList(service.listerMedicament());
        cbo.setItems(obMedocs);
    }
    public void loadComboBoxPosologie(){
        cboPosologie.getItems().addAll("une fois par jour","deux fois par jour","trois fois par jour");
    }

    public void loadComboBoxCodePatient(ComboBox<String> cbo){
        obCode = FXCollections.observableArrayList(service.listCodePatientById(ConnexionController.getCrtl().getUser().getId()));
        cbo.setItems(obCode);

    }
    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
