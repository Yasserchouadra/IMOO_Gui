package sample;


import Noyau.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherAnnoncesController  implements Initializable{
    private ObservableList<Bien> biensObservableList  = FXCollections.observableArrayList(Agence.treeBiens);

    @FXML
    private ListView<Bien> biensListView;


    @FXML
    private Label typeBienLabel = new Label();

    @FXML
    private Label transactionLabel = new Label();

    @FXML
    private Label dateLabel = new Label();

    @FXML
    private Label adresseLabel = new Label();

    @FXML
    private Label wilayaLabel = new Label();

    @FXML
    private Label superficieLabel = new Label();

    @FXML
    private Label simplexeLabel = new Label();

    @FXML
    private Label nbchambesLabel= new Label();

    @FXML
    private Label meubleLabel = new Label();

    @FXML
    private Label jardinLabel= new Label();

    @FXML
    private Label garageLabel = new Label();

    @FXML
    private Label prixLabel = new Label();

    @FXML
    private Label negoLabel = new Label();

    @FXML
    private Label propLabel = new Label();


    @FXML
    private ChoiceBox<String> typeTransactionChoiceBox;

    @FXML
    private ChoiceBox<String> wilayasChoiceBox;

    @FXML
    private ChoiceBox<String> propsChoiceBox;

    @FXML
    private ChoiceBox<String> typeBienChoiceBox;

/*
    @FXML
    private Slider prixMinSlider;*/

    @FXML
    private TextField prixMinTextField;


    @FXML
    private TextField superficieMinTextField;





    @FXML
    Button filtrerButton = new Button();

    @FXML
    ImageView filterIcon= new ImageView();


    /*
    ici les champs à modifier
     */
/*

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
*/



    public static class BienCell extends ListCell<Bien> {





        HBox hBox = new HBox();
        VBox vbox = new VBox();
        VBox titleVBox = new VBox();
        VBox buttonsVbox = new VBox();

        Image house;
        ImageView houseIcon = new ImageView(house);
        Pane pane = new Pane();

        Label typeBienLabel = new Label("");
        Label typeTransaction = new Label("");
        Label adresse = new Label("");
        Label superficie = new Label("");

        Label prop = new Label();
        Label descritption = new Label();

        Label prix = new Label();


        Button deleteButton = new Button("Delete");
        Button modifierButton = new Button("Modifier");



        public BienCell() {
            super();

            titleVBox.getChildren().addAll(typeBienLabel, typeTransaction);
            vbox.getChildren().addAll(titleVBox, adresse, superficie);
            buttonsVbox.getChildren().addAll(deleteButton, modifierButton);

            hBox.prefHeight(100);
            hBox.setSpacing(10);
            if (Agence.access)
                hBox.getChildren().addAll(houseIcon, vbox, pane, prix, buttonsVbox);
            else
                hBox.getChildren().addAll(houseIcon, vbox, pane, prix);

            hBox.setHgrow(pane, Priority.ALWAYS);

        }




        public void updateItem(Bien bien, boolean empty){
            super.updateItem(bien, empty);
            setText(null);
            setGraphic(null);

            if(bien != null && !empty){
               // affectation des champs

                typeBienLabel.setText(bien.getClass().getSimpleName());
                typeBienLabel.prefHeight(10);
                typeTransaction.setText(bien.getTransaction().toString());

                adresse.setText(bien.getAddresse()+" - "+bien.getWilaya().getNom());
                superficie.setText(bien.getSuperficie()+" m²");
                prop.setText("Bien de : "+bien.getProp().getPrenom()+" "+bien.getProp().getNom());

                prix.setText(String.valueOf(bien.calculPrix(bien.getTransaction(), true))+" DA");



                if (bien.getClass().getSimpleName().equals("Maison")){
                    house = new Image("images/maison.png");;

                }else
                if (bien.getClass().getSimpleName().equals("Appartement")) {
                    house = new Image("images/appart.png");
                }
                else
                if (bien.getClass().getSimpleName().equals("Terrain")) {
                    house = new Image("images/terrain.png");
                }
                houseIcon.setImage(house);
                houseIcon.setFitHeight(50);
                houseIcon.setFitWidth(50);
                    setGraphic(hBox);



                deleteButton.setOnAction(e -> {
                    Bien bienSelected;
                    bienSelected = getListView().getItems().remove(getIndex());
                    Agence.treeBiens.remove(bienSelected);

                });

/*

                modifierButton.setOnAction( e-> {
                    Parent samp = null;
                    try {
                        samp = FXMLLoader.load(getClass().getResource("modifierAppart.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    Scene sampscene = new Scene(samp);
                    Stage window = new Stage();

                    window.initModality(Modality.APPLICATION_MODAL);// to still use other windeows
                    window.setTitle("Modifier Bien");

                    window.setScene(sampscene);
                    window.setResizable(false);
                    window.showAndWait();

                });
*/




                deleteButton.setOnMouseEntered(e -> {
                    deleteButton.setStyle("-fx-background-color: #558ff2");
                });
                deleteButton.setOnMouseExited(e -> {
                    deleteButton.setStyle("");
                });
            }

        }
    }



    private void displayDetails(Bien bien){

        typeBienLabel.setText(bien.getClass().getSimpleName());
        transactionLabel.setText(bien.getTransaction().toString());
        dateLabel.setText(bien.getDateAjout().toString());
        adresseLabel.setText(bien.getAddresse());
        wilayaLabel.setText(bien.getWilaya().getNom());
        superficieLabel.setText(String.valueOf(bien.getSuperficie()));

        if (bien.getClass().getSimpleName().equalsIgnoreCase("appartement"))
        simplexeLabel.setText("---");
        nbchambesLabel.setText("---");


        meubleLabel.setText("----");
        jardinLabel.setText("----");
        garageLabel.setText("---");
        propLabel.setText(bien.getProp().getNom()+" "+bien.getProp().getPrenom());
        if (bien.getTransaction()==Transaction.location)
        prixLabel.setText(String.valueOf(bien.calculPrix(bien.getTransaction(), true))+ "  DA/Mois");
        else
            prixLabel.setText(String.valueOf(bien.calculPrix(bien.getTransaction(), true))+ "DA");

        negoLabel.setText(Bien.boolToString(bien.isNegociable()));

    }


    static class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }


    public void modifierButtonPushed(ActionEvent event) throws IOException {

        System.out.println(Agence.treeBiens.toArray()[0]);


        Parent samp = FXMLLoader.load(getClass().getResource("modifierAppart.fxml"));

        Scene sampscene = new Scene(samp);
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);// to still use other windeows
        window.setTitle("Modifier Bien");

        window.setScene(sampscene);
        window.setResizable(false);
        window.showAndWait();

    }



    public void choixCriteres(ActionEvent event){


        boolean criteres[] = new boolean[10];

        int nbCriteres=0, cpt;

        double prixMin = 0;

        float superficieMin = 0;

        Transaction tr = null;
        String tpBien = null;
        Wilaya wilaya = null;


        /** COMMENTAIRES sur le fonctionnement de cette methode
         *  le tableau de boolean
         *  premiere case : (critere de prix min)true : veut dire on prend l critere
         *  deuxieme case : (superficie min)
         *  troisieme case: pour wilaya
         *  quatrieme type de bien
         *  cinqieme transaction
         *
         */

        if(!(prixMinTextField.getText().equals("0"))) {
            criteres[0] = true;
        }

        if (!(superficieMinTextField.getText().equals("0"))){
            criteres[1] =true;
        }

        if (!wilayasChoiceBox.getValue().equals("Toutes")) {
            criteres[2] = true;
        }

        if (!typeBienChoiceBox.getValue().equals("Tous")) {
            criteres[3] = true;
        }

        if(!(typeTransactionChoiceBox.getValue().equalsIgnoreCase("Toutes"))){
            criteres[4] = true;
        }

        if (!propsChoiceBox.getValue().equals("Tous")){
            criteres[5] = true;
        }


        //biensValides.addAll(biens);
        ArrayList<Bien> biensValides = new ArrayList<>(Agence.treeBiens);

        for(Bien bien : Agence.treeBiens){
            int i =0;cpt = 0;
            while((i < criteres.length)){

                if (criteres[i]){
                    //cpt++;
                    switch (i){
                        case 0: if (bien.calculPrix(bien.getTransaction(), true) < Double.parseDouble(prixMinTextField.getText()))
                            biensValides.remove(bien);
                            break;
                        case 1: if(bien.getSuperficie() < Float.parseFloat(superficieMinTextField.getText()))
                            biensValides.remove(bien);
                            break;
                        case 2: if(!(bien.getWilaya().getNom().equals(wilayasChoiceBox.getValue())))
                            biensValides.remove(bien);
                            break;
                        case 3: if(!(bien.getClass().getSimpleName().equals(typeBienChoiceBox.getValue())))
                            biensValides.remove(bien);
                            break;
                        case 4: if(!(bien.getTransaction().toString().equalsIgnoreCase((typeTransactionChoiceBox.getValue()))))
                            biensValides.remove(bien);
                            break;
                        case 5: if(!((bien.getProp().getNom()+" "+bien.getProp().getPrenom()).equalsIgnoreCase((propsChoiceBox.getValue()))))
                            biensValides.remove(bien);
                            break;


                    }
                }
                i++;
            }
        }

        System.out.println(biensValides);

        biensObservableList = FXCollections.observableArrayList(biensValides);
        biensListView.setItems(biensObservableList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        biensListView.setItems(biensObservableList);
        biensListView.setCellFactory(param -> new BienCell() {
        });


        biensListView.setOnMouseClicked(new AfficherPropsController.ListViewHandler() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                Agence.bienAModifie = biensListView.getSelectionModel().getSelectedItem();
                displayDetails(biensListView.getSelectionModel().getSelectedItem());

            }
        });


        /**
         * region criteres
         */



        // filtre superficie et prix min

        superficieMinTextField.setText("0");
        prixMinTextField.setText("0");


// filtre prop
        propsChoiceBox.getItems().add("Tous");
        propsChoiceBox.setValue("Tous");
        for (
                Proprietaire prop : Proprietaire.proprietaires
        ) {
            propsChoiceBox.getItems().add(prop.getNom() + " " + prop.getPrenom());
        }


// filtre wilaya

        wilayasChoiceBox.getItems().add("Toutes");

        for (Wilaya wil : Wilaya.wilayas
        ) {
            wilayasChoiceBox.getItems().add(wil.getNom());
        }
        wilayasChoiceBox.setValue("Toutes");


        Util.enlightenButtonNormal(filtrerButton);




        // type bien

        typeBienChoiceBox.getItems().add("Tous");

        typeBienChoiceBox.getItems().add("Appartement");
        typeBienChoiceBox.getItems().add("Maison");
        typeBienChoiceBox.getItems().add("Terrain");

        typeBienChoiceBox.setValue("Tous");




        // type transaction

        typeTransactionChoiceBox.getItems().add("Toutes");
        typeTransactionChoiceBox.setValue("Toutes");

        typeTransactionChoiceBox.getItems().add("vente");
        typeTransactionChoiceBox.getItems().add("location");
        typeTransactionChoiceBox.getItems().add("echange");




        Image filterImage = new Image("images/filter.png");
        filterIcon.setImage(filterImage);


        /**
         * affectation pour la fenetre de modification
         */


        biensListView.setOnMouseClicked(new AfficherPropsController.ListViewHandler() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {


            }
        });











    }




}
