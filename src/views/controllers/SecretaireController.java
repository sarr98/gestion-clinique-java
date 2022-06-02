package views.controllers;

import Entities.RendezVous;
import Service.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class SecretaireController implements Initializable {

    Services service = new Services();

    private ObservableList<String> obCodePatient;

    private ObservableList<Integer> obIdRV;

    @FXML
    private ComboBox<String> cboCodePatient;

    @FXML
    private ComboBox<Integer> cboIDRV;

    @FXML
    private TextField txtfIDRV;

    private ObservableList<Integer> obId;

    @FXML
    private TextField txtfcodePatient;

    @FXML
    private Button deconnexion;

    @FXML
    private TableColumn<RendezVous, String> tblcCodePatient;

    @FXML
    private TableColumn<RendezVous, Date> tblcDate;

    @FXML
    private TableColumn<RendezVous, String> tblcEtat;

    @FXML
    private TableColumn<RendezVous, Time> tblcHeure;

    @FXML
    private TableColumn<RendezVous, Integer> tblcIDRV;

    @FXML
    private TableColumn<RendezVous, String> tblcTypeRV;

    @FXML
    private TableView<RendezVous> tblvSecretaire;

    @FXML
    private Text txtfBinevenue;

    private RendezVous rvSelect;

    private void loadTableView(){
        List<RendezVous> rdv=service.listerRendezVous();
        ObservableList<RendezVous> obvrdv = FXCollections.observableArrayList(rdv);
        //System.out.println(obvrdv);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tblcTypeRV.setCellValueFactory(new PropertyValueFactory<>("typeRdv"));
        tblcIDRV.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblcCodePatient.setCellValueFactory(new PropertyValueFactory<>("codePat"));
        tblvSecretaire.setItems(obvrdv);
    }

    public void loadComboBoxCodePatient(ComboBox<String> cbo){
        obCodePatient = FXCollections.observableArrayList(service.listCodePatient());
        cbo.setItems(obCodePatient);

    }

    public void loadComboBoxid(ComboBox<Integer> cbo){
        obId = FXCollections.observableArrayList(service.listIdRdv());
        cbo.setItems(obId);

    }


    @FXML
    void handleAnnulation(MouseEvent event) {

        if(service.changeStatusAnnuler(cboIDRV.getValue()) != 0){

        }
        showAlert("MESSAGE","Rendez Vous annulée", Alert.AlertType.INFORMATION);
        txtfcodePatient.clear();
        cboIDRV.valueProperty().set(null);
        loadTableView();



    }

    @FXML
    void handleConfirmation(MouseEvent event) {
         if(service.changeStatusConfirm(cboIDRV.getValue()) != 0){

        }
        showAlert("MESSAGE","Rendez-vous Confirmer", Alert.AlertType.CONFIRMATION);
         txtfcodePatient.clear();
        cboIDRV.valueProperty().set(null);
        loadTableView();


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxid(cboIDRV);
        loadTableView();
        txtfBinevenue.setText("Bienvenue , " + ConnexionController.getCrtl().getUser().getPrenom() + " " +ConnexionController.getCrtl().getUser().getNom() );
    }

    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void handleSelectRV(MouseEvent event) {
        rvSelect = tblvSecretaire.getSelectionModel().getSelectedItem();
        txtfcodePatient.setText(rvSelect.getCodePat());
        cboIDRV.getSelectionModel().select(rvSelect.getId());
    }
}
