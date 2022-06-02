package views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class MedecinController implements Initializable {

    @FXML
    private AnchorPane anchorContent;


    @FXML
    private Text txtBienvenue;

    @FXML
    private Button deconnexion;

    @FXML
    void handleConsultation(MouseEvent event) {
        try {
            loadView("v_faire_consultation");
        } catch (IOException e) {
            e.printStackTrace();
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
    void handleDetails(MouseEvent event) {
        try {
           loadView("v_details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadView(String view) throws IOException {
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtBienvenue.setText("Bienvenue DR, " + ConnexionController.getCrtl().getUser().getPrenom() + " " +ConnexionController.getCrtl().getUser().getNom());

        try {
            loadView("v_faire_consultation");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
