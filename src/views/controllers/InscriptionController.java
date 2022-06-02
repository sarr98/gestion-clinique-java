package views.controllers;

import Dao.PatientDao;
import Entities.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class InscriptionController implements Initializable {

    private PatientDao patientdao = new PatientDao();

    @FXML
    private ComboBox<String> cboAntecedant;

    @FXML
    private Text txtError;

    @FXML
    private TextField txtfDomicile;

    @FXML
    private TextField txtfEmail;

    @FXML
    private TextField txtfLogin;

    @FXML
    private TextField txtfNom;

    @FXML
    private PasswordField txtfPassword;

    @FXML
    private Text txtfValidateCodePostal;

    @FXML
    private Text txtfValidateDomicile;

    @FXML
    private Text txtfValidateLogin;

    @FXML
    private Text txtfValidateName;

    @FXML
    private Text txtfValidatePrenom;

    @FXML
    private Text txtfValidateTelephone;

    @FXML
    private TextField txtfPrenom;

    @FXML
    private TextField txtfTelephone;

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_inscrire;

    private String antecedant1="Diabete";
    private String antecedant2="Hypertension";
    private String antecedant3="Cardiaque";
    private String antecedant4="Operation Palvienne";
    private String antecedant5="Asthme";
    private String antecedant6="Autres";

    @FXML
    void handleInscription(MouseEvent event) {
        String telephone=txtfTelephone.getText();
        int numTel=Integer.parseInt(telephone);
        Patient patient = new Patient(txtfDomicile.getText(),cboAntecedant.getSelectionModel().getSelectedItem().toString(),txtfNom.getText(),txtfEmail.getText(),txtfPrenom.getText(),txtfLogin.getText(),txtfPassword.getText(),numTel,"PATIENT");
        patientdao.insert(patient);
        AnchorPane root;
        try {
            Stage stage1 =(Stage) btn_inscrire.getScene().getWindow();
            stage1.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void handleReset(MouseEvent event) {
        AnchorPane root;
        try {
            Stage stage =(Stage) btn_annuler.getScene().getWindow();
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
        loadComboBox();
    }
    public void loadComboBox(){
        cboAntecedant.getItems().addAll(antecedant1,antecedant2,antecedant3,antecedant4,antecedant5,antecedant6);
    }
    @FXML
    void validationDomicile(KeyEvent event) {
        String domicile = this.txtfDomicile.getText();
        if (Validator.isUsername(domicile)){
            this.btn_inscrire.setDisable(false);
            txtfValidateDomicile.setVisible(false);
        }else {
            txtfValidateDomicile.setVisible(true);
            this.btn_inscrire.setDisable(true);
        }
    }

    @FXML
    void validationLogin(KeyEvent event) {
        String login = this.txtfLogin.getText().trim();
        if (Validator.isValidEmailAddress(login)) {
            this.btn_inscrire.setDisable(false);
            txtfValidateLogin.setVisible(false);
        } else {
            txtfValidateLogin.setVisible(true);
            this.btn_inscrire.setDisable(true);
        }
    }

    @FXML
    void validationNom(KeyEvent event) {
        String nom = this.txtfNom.getText();
        if (Validator.isUsername(nom)){
            this.btn_inscrire.setDisable(false);
            txtfValidateName.setVisible(false);
        }else {
            txtfValidateName.setVisible(true);
            this.btn_inscrire.setDisable(true);
        }
    }

    @FXML
    void validationPrenom(KeyEvent event) {
        String prenom = this.txtfPrenom.getText();
        if (Validator.isUsername(prenom)){
            this.btn_inscrire.setDisable(false);
            txtfValidatePrenom.setVisible(false);
        }else {
            txtfValidatePrenom.setVisible(true);
            this.btn_inscrire.setDisable(true);
        }
    }

    @FXML
    void validationTelephone(KeyEvent event) {
        /*String tel = this.txtfTelephone.getText();
        if (Validator.isTel(tel)){
            this.btn_inscrire.setDisable(false);
            txtfValidateTelephone.setVisible(false);
        }else {
            txtfValidateTelephone.setVisible(true);
            this.btn_inscrire.setDisable(true);
        }*/
    }
}
