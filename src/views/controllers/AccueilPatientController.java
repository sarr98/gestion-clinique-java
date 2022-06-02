package views.controllers;

import Entities.*;
import Service.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class AccueilPatientController implements Initializable {

    private Services service = new Services();

    private ConnexionController crtl;

    private ObservableList<Medecin> medecinObservableList;

    @FXML
    private Text date;

    @FXML
    private Button planifier;


    @FXML
    private Text heure;

    @FXML
    private Text txtfBienvenue;

    @FXML
    private Button annulerRV;

    @FXML
    private Button deconnexion;

    @FXML
    private Button listerRV;

    @FXML
    private ComboBox<String> cboHeure;

    @FXML
    private AnchorPane anchorContent;

    @FXML
    private ComboBox<Medecin> cboMedecin;

    @FXML
    private ComboBox<String> cboPrestation;

    @FXML
    private ComboBox<String> cboTypeRV;

    @FXML
    private Text changerType;

    @FXML
    private DatePicker txtfDate;


    String type1="CONSULTATION";

    String type2="PRESTATION";

    public void loadComboBoxRDV(){
        cboTypeRV.getItems().addAll(type1,type2);
    }
    public void loadComboBoxPrestation(){
        String type1="BIOPSIE";
        String type2="IRM";
        String type3="RADIOTHERAPIE";
        String type4="TRAITEMENT DE REEDUCATION FONCTIONNELLE";
        String type5="DRAINAGE LYMPHATIQUE";
        String type6="VACCINATION";
        cboPrestation.getItems().addAll(type1,type2,type3,type4,type5,type6);
    }
    public void loadComboBoxMedecin(ComboBox<Medecin> cbo){
      medecinObservableList = FXCollections.observableArrayList(service.selectionnerMedecin());
        cbo.setItems(medecinObservableList);
    }

    public void loadComboBoxHour(){
        cboHeure.getItems().addAll("8:00:00","9:00:00","10:00:00","11:00:00","12:00:00","15:00:00","16:00:00","17:00:00","18:00:00");
    }

    @FXML
    void handleConfirmation(MouseEvent event) {
        if (cboTypeRV.getSelectionModel().getSelectedItem().toString().equals(type1)){
            date.setVisible(true);
            heure.setVisible(true);
            txtfDate.setVisible(true);
            cboHeure.setVisible(true);
            cboMedecin.setVisible(true);
            cboPrestation.setVisible(false);
            changerType.setText("MEDECIN");
            planifier.setVisible(true);
            changerType.setVisible(true);
        }else {
            if (cboTypeRV.getSelectionModel().getSelectedItem().toString().equals(type2)){
                changerType.setText("PRESTATION");
                changerType.setVisible(true);
                date.setVisible(true);
                heure.setVisible(true);
                txtfDate.setVisible(true);
                cboHeure.setVisible(true);
                cboMedecin.setVisible(false);
                cboPrestation.setVisible(true);
                planifier.setVisible(true);
            }
        }
    }

    @FXML
    void handleDeconnexion(MouseEvent event) {
        AnchorPane root;
        try {
            Stage stage =(Stage) deconnexion.getScene().getWindow();
            stage.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void handleDeleteRV(MouseEvent event) {

        try {
           loadView("v_annulerRV");
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }

    @FXML
    void handlePlanifier(MouseEvent event) {
        LocalDate str = txtfDate.getValue();
        Date datee = Date.valueOf(str);
        Time time = Time.valueOf(cboHeure.getSelectionModel().getSelectedItem());
        Users user =ConnexionController.getCrtl().getUser();
        Patient pat = service.getPatientByUser(user);
        String codePat=pat.getCode();
        String typeRDV=cboTypeRV.getSelectionModel().getSelectedItem().toString();
        if(typeRDV.equals("CONSULTATION")){
            if (!service.verifRDV(cboMedecin.getSelectionModel().getSelectedItem(),datee,time,cboTypeRV.getSelectionModel().getSelectedItem().toString(),pat)){}
                showAlert("MESSAGE","Votre rendez-vous a été enregistré avec succes", Alert.AlertType.CONFIRMATION);
            int idUser=cboMedecin.getSelectionModel().getSelectedItem().getId();
            Consultation consultation = new Consultation(idUser,datee,time,typeRDV,codePat);
            service.demanderConsultation(consultation);
            changerType.setVisible(false);
            date.setVisible(false);
            cboHeure.setVisible(false);
            txtfDate.setVisible(false);
            cboPrestation.setVisible(false);
            cboMedecin.setVisible(false);
            cboHeure.valueProperty().set(null);
            cboHeure.setVisible(false);
            heure.setVisible(false);
            cboMedecin.valueProperty().set(null);
            cboMedecin.setVisible(false);
            cboTypeRV.valueProperty().set(null);
            planifier.setVisible(false);
        }else {
            showAlert("Message","Heure Indisponible",Alert.AlertType.ERROR);
        }
         if (typeRDV.equals("CONSULTATION")){
             Prestation prestation = new  Prestation(typeRDV,datee,time,codePat);
             service.demanderPrestation(prestation);
             changerType.setVisible(false);
             date.setVisible(false);
             txtfDate.setVisible(false);
             cboPrestation.setVisible(false);
             cboMedecin.setVisible(false);
             cboHeure.valueProperty().set(null);
             cboHeure.setVisible(false);
             heure.setVisible(false);
             cboMedecin.valueProperty().set(null);
             cboMedecin.setVisible(false);
             cboTypeRV.valueProperty().set(null);
             planifier.setVisible(false);
             cboPrestation.valueProperty().set(null);
         }else {
             showAlert("Message","Heure Indisponible",Alert.AlertType.ERROR);
         }
    }

    @FXML
    void handlePlanifierRV(MouseEvent event) {
        AnchorPane root;
        try {
            Stage stage =(Stage) deconnexion.getScene().getWindow();
            stage.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_accueil_patient.fxml"));
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void handleShowRV(MouseEvent event) {
        AnchorPane root;
        try {
          loadView("v_listerRV");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       txtfBienvenue.setText("Bienvenue ,  " + " " + ConnexionController.getCrtl().getUser().getPrenom() + " " +ConnexionController.getCrtl().getUser().getNom());
        loadComboBoxRDV();
        loadComboBoxMedecin(cboMedecin);
        loadComboBoxPrestation();
        loadComboBoxHour();
    }


    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);

    }
}
