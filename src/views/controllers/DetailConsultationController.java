package views.controllers;

import Entities.Ordonnance;
import Entities.Users;
import Service.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailConsultationController implements Initializable {

    private ObservableList<String> obCode;

    Services service = new Services();

    @FXML
    private AnchorPane anchorDetails;

    @FXML
    private AnchorPane anchorTableViews;

    @FXML
    private ComboBox<String> cboCodePatient;

    @FXML
    private TableColumn<Ordonnance, String> tblcMedicament;

    @FXML
    private TableColumn<Ordonnance, String> tblcPosologie;

    @FXML
    private TableView<Ordonnance> tblvDetailsPatient;

    @FXML
    private TextField txtfAntecedants;

    @FXML
    private TextField txtfNom;

    @FXML
    private TextField txtfPrenom;

    @FXML
    private TextField txtfTelephone;

    @FXML
    void handleSearch(MouseEvent event) {
        anchorDetails.setVisible(true);
        anchorTableViews.setVisible(true);
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
        loadTableView();
    }
    public void loadComboBoxCodePatient(ComboBox<String> cbo){
        obCode = FXCollections.observableArrayList(service.listCodePatientById(ConnexionController.getCrtl().getUser().getId()));
        cbo.setItems(obCode);

    }
    private void loadTableView(){
        List<Ordonnance> ordo=service.selectOrdonnance(ConnexionController.getCrtl().getUser().getId(), cboCodePatient.getValue());
        ObservableList<Ordonnance> obvOrdo = FXCollections.observableArrayList(ordo);
        //System.out.println(ordo);
        tblcMedicament.setCellValueFactory(new PropertyValueFactory<>("nomMedocs"));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvDetailsPatient.setItems(obvOrdo);
    }
}
