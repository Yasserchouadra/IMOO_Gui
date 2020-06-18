package sample;

import Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterBienAppartController implements Initializable {



    @FXML
    private ChoiceBox<String> typeTransactionChoiceBox = new ChoiceBox<String>();
    @FXML
    private ChoiceBox<String> propsChoiceBox = new ChoiceBox<String>();
    @FXML
    private ImageView plusIcon;
    @FXML private ImageView reloadIcon = new ImageView();
/*****************************************/
/*
here i will decalre  the texfields to create an appart
 */

    @FXML
    private TextField matriculeTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField wilayaCodeTextField;

    @FXML
    private TextField superficieTextField;

    @FXML
    private TextField nbPiecesTextField;
    @FXML
    private TextField etageTextField;
    @FXML
    private TextField prixInitialTextField;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private CheckBox ascenseurCheckBox;
    @FXML
    private CheckBox meubleCheckBox;
    @FXML
    private CheckBox simplexeCheckBox;

    @FXML
    private ChoiceBox<String> wilayasChoiceBox;

    /*******/


    public void creerPropDialogBox (ActionEvent event) throws IOException {

        Parent samp = FXMLLoader.load(getClass().getResource("test.fxml"));
        Scene sampscene = new Scene(samp);
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);// to still use other windeows
        window.setTitle("Créer un nouveau proprietaire");

        window.setScene(sampscene);
        window.setResizable(false);
        window.showAndWait();
    }




    public void refreshList(ActionEvent event){

        for (Proprietaire prop: Proprietaire.proprietaires
        ) {
            if (propsChoiceBox.getItems().contains(prop.getNom()+" "+prop.getPrenom()))
                continue;
            else {
                propsChoiceBox.getItems().add(prop.getNom()+" "+prop.getPrenom());

            }
        }
    }


    public void creerBienAppart(ActionEvent event){
    LocalDate.now();
    String err=null;

    try {
        Bien ap = new Appartement(Integer.parseInt(matriculeTextField.getText()),
                adresseTextField.getText(), Float.parseFloat(superficieTextField.getText()),
                Proprietaire.stringToProprietaire(propsChoiceBox.getValue()),Transaction.valueOf(typeTransactionChoiceBox.getValue()),
                Double.parseDouble(prixInitialTextField.getText()), false, descriptionTextArea.getText(),
                LocalDate.now(), Wilaya.stringToWilaya(wilayasChoiceBox.getValue()),
                "Photo", Integer.parseInt(nbPiecesTextField.getText()), meubleCheckBox.isSelected(),
                Integer.parseInt(etageTextField.getText()), simplexeCheckBox.isSelected(), ascenseurCheckBox.isSelected());
        Agence.treeBiens.add(ap);
        ap.afficher();
    }catch (Exception e){
        err = e.getMessage();
    }

        if(err!=null){
        Alert alert = new Alert(Alert.AlertType.INFORMATION );
        alert.setTitle("ERREUR d'entrée");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors du remplissage les champs ! "+"\n"+
                "Veuillez revoir vos entrées de données\n "+err);
        alert.showAndWait();
    }

    }













    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeTransactionChoiceBox.getItems().add("vente");
        typeTransactionChoiceBox.getItems().add("location");
        typeTransactionChoiceBox.getItems().add("echange");


        for (Proprietaire prop: Proprietaire.proprietaires
        ) {propsChoiceBox.getItems().add(prop.getNom()+" "+prop.getPrenom());
        }


        for (Wilaya wil: Wilaya.wilayas
             ) {wilayasChoiceBox.getItems().add(wil.getNom());
        }



        Image plus = new Image("images/plus.png");
        plusIcon.setImage(plus);
        reloadIcon.setImage(new Image("images/reload.png"));


    }
}
