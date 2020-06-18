package sample;

import Noyau.Proprietaire;
import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TestController {

    @FXML
    private ImageView addIcon;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField adressTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextfield;

    @FXML
    void creerProp(ActionEvent event){
        Proprietaire prop = new Proprietaire(0, nomTextField.getText(), prenomTextField.getText(),
                emailTextField.getText(),phoneTextfield.getText(), adressTextField.getText() );
    }


}