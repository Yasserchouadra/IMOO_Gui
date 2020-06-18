package sample;



import Noyau.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {


    public Agence ImmoESI = new Agence();
    Admin ad = new Admin("chems","0000");


    /**
     * declarations
     *
     *
     */
    @FXML
    private SubScene mainSubScene;



    public void GoToAdmin (ActionEvent event) throws IOException
    {
        Parent admin = FXMLLoader.load(getClass().getResource("PageAdmin.fxml"));

        Scene adminscene = new Scene(admin);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(adminscene);
        window.setResizable(false);
        window.show();
    }
    public void quitter ()
    {
        int i=0;
        System.exit(i);
    }


    public void GoToPublic (ActionEvent event) throws IOException
    {
        Parent publiq = FXMLLoader.load(getClass().getResource("PagePublic.fxml"));
        Scene pubscene = new Scene(publiq);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Agence.access =false;

        window.setScene(pubscene);
        window.setResizable(false);
        window.show();
    }



    public void GoBACK (ActionEvent event) throws IOException
    {
        Parent samp = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene sampscene = new Scene(samp);


        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(sampscene);
        window.setResizable(false);
        window.show();
    }

    @FXML


    void OnSubit(ActionEvent event) throws IOException, InterruptedException {

        boolean exist = ad.identification(username.getText(),passwd.getText());
        //boolean exist = true;
        if (exist)
        {
            Agence.access = true;

           // Parent board = FXMLLoader.load(getClass().getResource("TableauDeBord.fxml"));
           // mainSubScene.setRoot(board);


            Parent samp = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
            Scene sampscene = new Scene(samp);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();



            window.setScene(sampscene);
            window.setResizable(true);
            window.show();
        }
        else
            matriculeNonTrouve("cc");

    }
    /*************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwd;

    public void matriculeNonTrouve(String m) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION );
        alert.setTitle("ERREUR IDENTIFICATION");
        alert.setHeaderText(null);
        alert.setContentText("ERREUR LORS D'IDENTIFICATION ! "+"\n"+
                "Veuillez introduire le BON USERNAME/PASSWORD une autre fois");
        alert.showAndWait();
    }

    public void GoBACKADMENU (ActionEvent event) throws IOException
    {
        Parent samp = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
        Scene sampscene = new Scene(samp);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(sampscene);
        window.setResizable(true);
        window.show();
    }

    public void GoGestionBiens (ActionEvent event) throws IOException
    {
        Parent samp = FXMLLoader.load(getClass().getResource("MenuGestionBiens.fxml"));
        Scene sampscene = new Scene(samp);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(sampscene);
        window.setResizable(false);
        window.show();
    }



    public void GoAjouterBien (ActionEvent event) throws IOException
    {
        Parent samp = FXMLLoader.load(getClass().getResource("AjouterBien.fxml"));
        Scene sampscene = new Scene(samp);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(sampscene);
        window.setResizable(true);
        window.show();
    }
/*******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
    @FXML
    private TextField zoname;
    @FXML
    private TextField zonprenom;
    @FXML
    private TextField zoneadrmail;
    @FXML
    private TextField zoneadr;
    @FXML
    private TextField zonetele;






/*****************************************************************************************************************************************/


/******************************************************************/

private TableView tableWilayas = new TableView();



public void GoAfficherWilayas (ActionEvent event) throws IOException
{
    Parent samp = FXMLLoader.load(getClass().getResource("AffichageWilayas.fxml"));
    mainSubScene.setRoot(samp);


}



@FXML Label wilayaName;
    public void wilayaPriceView(){
        for (Wilaya wil:Wilaya.wilayas
        ) {

        }
    }


/**************************************************************/
// controlling message

@FXML private Button MessageSceneBackButton;
@FXML private TextField senderName;
@FXML private TextField email;
@FXML private TextArea textMessage;
@FXML private Button EnvoyerMessage;
@FXML private Label messageSent;



public void GoMessage (ActionEvent event) throws IOException, MessagingException {

    Parent samp = FXMLLoader.load(getClass().getResource("Message.fxml"));
    Scene sampscene = new Scene(samp);
    Stage window = new Stage();

    window.initModality(Modality.APPLICATION_MODAL);// to still use other windeows
    window.setTitle("Envoyer Message");


    window.setScene(sampscene);
    window.setResizable(false);
    window.showAndWait();

}

public void sendMessage(ActionEvent event) throws MessagingException {
    Bien b=null;
    Message2 message = new Message2(senderName.getText(),email.getText(),  textMessage.getText(), b);
}


public void EnvoyerMessage(ActionEvent event){
   // Bien b = null;
    try {
        Message2 message = new Message2(senderName.getText(),email.getText(),  textMessage.getText(), Proprietaire.proprietaires.get(0).getBiens().get(0));
    } catch (MessagingException ex) {
        ex.printStackTrace();
    }
    successMessageLabel.setText("message envoyé avec succès");

}

/**************************************************************/
/******************************************/

public void mouseEnterButton(ActionEvent Event){


}



public void AfficheAnnonceButtonPushed(ActionEvent event) throws IOException {
    Parent samp = FXMLLoader.load(getClass().getResource("AfficherAnnonces.fxml"));
    mainSubScene.setRoot(samp);
}


    public void afficherPropButtonPushed(ActionEvent event) throws IOException {
        Parent samp = FXMLLoader.load(getClass().getResource("AfficherProps.fxml"));
        mainSubScene.setRoot(samp);
    }



    public void goBoiteMessages(ActionEvent event) throws IOException {
        Parent samp = FXMLLoader.load(getClass().getResource("MessagesBoite.fxml"));
        mainSubScene.setRoot(samp);
    }


    /******************************************************/

//Ajouter bien

        public void ajouterBien() throws IOException {
            /* declarations */
            char choix;
            Scanner sc = new Scanner(System.in);
            Proprietaire prop=null;
            try{

                    choix = sc.next().charAt(0);

                    switch (choix){
                        case '1':prop = ImmoESI.selectProp();
                            break;
                        case '2':prop =  new Proprietaire();
                            Proprietaire.proprietaires.add(prop);
                            break;
                        default: System.out.println("choix 1 ou 2");
                            break;
                    }


                Transaction tr=ImmoESI.typeTransaction(); // choix de transaction

                String typeBien = ImmoESI.typeBien(); // selection de type de bien : appart , maison, terrrain

                switch (typeBien){
                    case "Appartement": ImmoESI.ajouterAppart(tr, prop);
                        break;
                    case "Maison":
                        ImmoESI.ajouterMaison(tr, prop);
                        break;
                    case "Terrain":
                        ImmoESI.ajouterTerrain(tr, prop);
                        break;
                }
            }catch (InputMismatchException | NegativeException e){
                System.out.println("ATTENTION ! \n Erreur a la saisie !!! ");
            }

    }





    public void AjouterBienAppart(ActionEvent event) throws IOException {

        Parent samp = FXMLLoader.load(getClass().getResource("AjouterBienAppart.fxml"));
        mainSubScene.setRoot(samp);
    }
    public void AjouterBienMaison(ActionEvent event) throws IOException {
        Parent samp = FXMLLoader.load(getClass().getResource("AjouterBienMaison.fxml"));
        mainSubScene.setRoot(samp);
    }
    public void AjouterBienTerrain(ActionEvent event) throws IOException {
        Parent samp = FXMLLoader.load(getClass().getResource("AjouterBienTerrain.fxml"));
        mainSubScene.setRoot(samp);
    }


    public void tabDeBord(ActionEvent event) throws IOException {
        Parent samp = FXMLLoader.load(getClass().getResource("TableaudeBord.fxml"));
        mainSubScene.setRoot(samp);
    }

/**************************************************/
/**************************************************/



/********************************************/
//Images

    @FXML
    private ImageView imageview = new ImageView();
    @FXML
    private ImageView admminIcon = new ImageView();
    @FXML
    private ImageView pubIcon = new ImageView();
    @FXML
    private ImageView mailImView = new ImageView();
    @FXML
    private ImageView addIcon = new ImageView();
    @FXML
    private ImageView plusIcon = new ImageView();
    @FXML
    private ImageView lockIcon = new ImageView();


    @FXML
    private PieChart pieChart = new PieChart();


    @FXML
    private PieChart pieChart2 = new PieChart();

    @FXML
    Hyperlink link;




    @FXML
    private Button adminButton = new Button();
    @FXML
    private Button publicButton = new Button();

    @FXML
    Button connectButton = new Button();


    /*
    message label
     */
    @FXML
    private Label successMessageLabel = new Label("");


    public void enlightenButtonTwice(Button btn){
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color: #F9C784");
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-background-color: #577280");
        });

    }


    @FXML
    private Button tabBord = new Button();

    @FXML
    private Button ajoutAppartButton = new Button();

    @FXML
    private Button ajoutTerrainButton = new Button();

    @FXML
    private Button afficherPropButton = new Button();



    




    public void enlightenButtonNormal(Button btn){
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color: #577280");
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("");
        });

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        enlightenButtonTwice(tabBord);
        enlightenButtonTwice(ajoutAppartButton);
        enlightenButtonTwice(ajoutTerrainButton);
        enlightenButtonTwice(afficherPropButton);

        enlightenButtonNormal(adminButton);
        enlightenButtonNormal(publicButton);


        enlightenButtonNormal(connectButton);

        //pieChart.setLegendSide(Side.LEFT);



        Image image = new Image("images/Immoesi.png");
        imageview.setImage(image);
        Image imageAdmin = new Image("images/admin-ui.png");
        admminIcon.setImage(imageAdmin);
        Image pubimage = new Image("images/public.png");
        pubIcon.setImage(pubimage);
        Image mailImage = new Image("images/mail.png");
        mailImView.setImage(mailImage);

        Image addicon = new Image("images/addUser.png");
        addIcon.setImage(addicon);


        lockIcon.setImage(new Image("images/locked.png"));




        successMessageLabel.setText("");




        /*
        hyper link
         */




    }
}


