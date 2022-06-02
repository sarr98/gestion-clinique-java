package views.controllers;

import Entities.Users;
import Service.Services;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ConnexionController implements Initializable {

    public static ConnexionController crtl;

    private Users user;

    private Services services = new Services();

    private AnchorPane anchorContent;

    @FXML
    private Button connecter;

    @FXML
    private Button inscription;

    @FXML
    private Text txtError;

    @FXML
    private TextField txtfLogin;

    @FXML
    private PasswordField txtfPassword;

    @FXML
    void handleConnexion(MouseEvent event) {
        String login = txtfLogin.getText().trim();
        String password = txtfPassword.getText();
        if (login.isEmpty() || password.isEmpty()) {
            txtError.setText("Login ou Password Incorrect");
        }else{
            user = services.seConnecter(login,password);
            if(user==null){
                txtError.setText("Login ou Password Incorrect");
                txtError.setVisible(true);
            }else {
                txtfLogin .clear();
                txtfPassword.clear();
                String role = "PATIENT";
                String role1 = "MEDECIN";
                String role2 = "SECRETAIRE";
                String role3 = "RESPONSABLE PRESTATION";
                if (user.getRole().equals(role)) {
                    AnchorPane root;
                    try {
                        Stage stage1 =(Stage) connecter.getScene().getWindow();
                        stage1.hide();
                        root = FXMLLoader.load(getClass().getResource("/views/v_accueil_patient.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (user.getRole().equals(role1)) {
                    AnchorPane root;
                    try {
                        Stage stage1 =(Stage) connecter.getScene().getWindow();
                        stage1.hide();
                        root = FXMLLoader.load(getClass().getResource("/views/v_medecin.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (user.getRole().equals(role2)) {
                    AnchorPane root;
                    try {
                        Stage stage1 =(Stage) connecter.getScene().getWindow();
                        stage1.hide();
                        root = FXMLLoader.load(getClass().getResource("/views/v_secretaire.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (user.getRole().equals(role3)) {
                    AnchorPane root;
                    try {
                        Stage stage1 =(Stage) connecter.getScene().getWindow();
                        stage1.hide();
                        root = FXMLLoader.load(getClass().getResource("/views/v_responsable_prestation.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }

    @FXML
    void handleInscription(MouseEvent event) {
        AnchorPane root;
        try {
            Stage stage1 =(Stage) inscription.getScene().getWindow();
            stage1.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_inscription.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void validate_login(KeyEvent event) {
        String login = this.txtfLogin.getText().trim();
        if (Validator.isValidEmailAddress(login)){
            this.connecter.setDisable(false);
            txtError.setVisible(false);
        }else {
            this.connecter.setDisable(true);
            txtError.setText("Login Invalide");
            txtError.setVisible(true);
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        crtl=this;
        this.connecter.setDisable(true);
    }
    public static ConnexionController getCrtl() {
        return crtl;
    }

    public Users getUser(){
        return user;
    }
}
