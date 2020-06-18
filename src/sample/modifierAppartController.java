package sample;

import Noyau.Agence;
import Noyau.Bien;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class modifierAppartController implements Initializable {
/*
    public modifierAppartController(Bien bien){


    }*/

    @FXML
    TextField typeBienTextField = new TextField();
    @FXML
    TextField transactionTextField = new TextField();
    @FXML
    TextField wilayaTextField = new TextField();
    @FXML
    TextField adresseTextField = new TextField();

    @FXML
    TextField dateajoutTextField = new TextField();

    @FXML
    TextField prixTextField = new TextField();
    @FXML
    TextField superficieTextField = new TextField();
    @FXML
    Toggle negociableToggleButton = new ToggleButton();

    @FXML
    Button modificationButton = new Button();
    @FXML
    Label modifieSuccess = new Label("");



    public static void modifier(){

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Bien bien = (Bien)Agence.treeBiens.toArray()[0];

        typeBienTextField.setText(bien.getClass().getSimpleName());
        transactionTextField.setText(bien.getTransaction().toString());
        wilayaTextField.setText(bien.getWilaya().getNom());
        adresseTextField.setText(bien.getAddresse());
        dateajoutTextField.setText(bien.getDateAjout().toString());
        prixTextField.setText(String.valueOf(bien.getPrixInit()));
        superficieTextField.setText(String.valueOf(bien.getSuperficie()));
        negociableToggleButton.setSelected(true);


    }
}
