
package sample;

import Noyau.Agence;
import Noyau.Bien;
import Noyau.Proprietaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherPropsController implements Initializable {


    private ObservableList<Proprietaire> propsObservableList  = FXCollections.observableArrayList(Proprietaire.proprietaires);



    private ObservableList<Bien> biensPropObservableList;

    @FXML
    private SubScene detailSubscene;

    @FXML
    private ListView<Proprietaire> proprietairesListView;

// here i define the list vies of a chosen person
    @FXML
    private ListView<Bien> biensPropListView;


    //define the needs for the details area

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
    private Label nbchambesLabel = new Label();

    @FXML
    private Label meubleLabel = new Label();

    @FXML
    private Label jardinLabel = new Label();

    @FXML
    private Label garageLabel = new Label();

    @FXML
    private Label prixLabel = new Label();

    @FXML
    private Label negoLabel = new Label();

    @FXML
    private Label propLabel = new Label();



    public static class PropCell extends ListCell<Proprietaire>
    {
        HBox hBox = new HBox();

        Pane pane =  new Pane();

        Label nomProp = new Label("");
        Label mailProp = new Label("");
        Button btn = new Button("delete");


        public PropCell(){
            super();
            if (Agence.access)
            hBox.getChildren().addAll(nomProp, mailProp, pane, btn);
            else
                hBox.getChildren().addAll(nomProp, mailProp, pane);

            hBox.setHgrow(pane, Priority.ALWAYS);
    }



    public void updateItem(Proprietaire prop, boolean empty){
            super.updateItem(prop, empty);
            setText(null);
            setGraphic(null);

            if(prop != null && !empty){
                nomProp.setText(prop.getNom()+" "+prop.getPrenom());
                mailProp.setText(" E-mail : "+prop.getadrMail());
                setGraphic(hBox);




                btn.setOnAction(e -> {
                    Proprietaire propSelected;
                    propSelected = getListView().getItems().remove(getIndex());
                    Proprietaire.proprietaires.remove(propSelected);

                    System.out.println("this was deleted  "+propSelected.getPrenom());

                });



                btn.setOnMouseEntered(e -> {
                    btn.setStyle("-fx-background-color: #fc4e03");
                });
                btn.setOnMouseExited(e -> {
                    btn.setStyle("");
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
        simplexeLabel.setText("--");
        nbchambesLabel.setText("---");

        meubleLabel.setText("----");

        jardinLabel.setText("----");
        garageLabel.setText("---");



        prixLabel.setText(String.valueOf(bien.calculPrix(bien.getTransaction(), true)));
        negoLabel.setText(Bien.boolToString(bien.isNegociable()));
        propLabel.setText(bien.getProp().getNom()+"  "+bien.getProp().getPrenom());
    }




    static class ListViewHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //this method will be overrided in next step
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        proprietairesListView.setItems(propsObservableList);
        proprietairesListView.setCellFactory(param -> new PropCell());

        int index=0;

//initialisation de la lste des biens d'un prop designé
        biensPropObservableList = FXCollections.observableArrayList(proprietairesListView.getItems().get(0).getBiens());



        proprietairesListView.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.print(proprietairesListView.getSelectionModel().getSelectedIndex());

                biensPropObservableList = FXCollections.observableArrayList(proprietairesListView.getSelectionModel().getSelectedItem().getBiens());

                biensPropListView.setItems(biensPropObservableList);
            }
        });


        biensPropListView.setItems(biensPropObservableList);
        biensPropListView.setCellFactory(parame -> new AfficherAnnoncesController.BienCell());


        biensPropListView.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                displayDetails(biensPropListView.getSelectionModel().getSelectedItem());

            }
        });

    }
}

