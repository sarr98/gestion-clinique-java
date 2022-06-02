package views.controllers;

import Entities.RendezVous;
import Service.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponsablePrestationController implements Initializable {

    Services service = new Services();

    private ObservableList<Date> obDate;

    @FXML
    private ComboBox<Date> cboDate;

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
    private TableColumn<RendezVous, Integer> tblcID;

    @FXML
    private TableView<RendezVous> tblvRDV;

    @FXML
    private Text txtfBinevenue;

    public void loadComboBoxDateRdv(ComboBox<Date> cbo){
        obDate = FXCollections.observableArrayList(service.listerDateRendezVous());
        cbo.setItems(obDate);
    }

    private void loadTableView(Date date){
        List<RendezVous> rdv=service.listerRdvParDate(date) ;
        ObservableList<RendezVous> obvrdv = FXCollections.observableArrayList(rdv);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tblcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblcCodePatient.setCellValueFactory(new PropertyValueFactory<>("codePat"));
        tblvRDV.setItems(obvrdv);
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
    void handleSearchDate(MouseEvent event) {
        loadTableView(cboDate.getValue());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxDateRdv(cboDate);
        txtfBinevenue.setText("Bienvenue , " + ConnexionController.getCrtl().getUser().getPrenom() + " " +ConnexionController.getCrtl().getUser().getNom() );

    }
}
