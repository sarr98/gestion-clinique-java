package views.controllers;

import Entities.RendezVous;
import Service.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class ListerRVController implements Initializable {

    Services service = new Services();

    @FXML
    private TableColumn<RendezVous, Date> tblcDate;

    @FXML
    private TableColumn<RendezVous, String> tblcEtat;

    @FXML
    private TableColumn<RendezVous, Time> tblcHeure;

    @FXML
    private TableColumn<RendezVous, String> tblcType;

    @FXML
    private TableView<RendezVous> tblvListeRV;



    private void loadTableView(){
        List<RendezVous> rdv=service.SelectAllRendezVous(ConnexionController.getCrtl().getUser().getCode());
        ObservableList<RendezVous> obvrdv = FXCollections.observableArrayList(rdv);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("typeRdv"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblvListeRV.setItems(obvrdv);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableView();
    }
}
