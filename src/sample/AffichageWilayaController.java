package sample;

import Noyau.Wilaya;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;


import javax.sql.rowset.serial.SerialStruct;
import java.net.URL;
import java.util.ResourceBundle;

public class AffichageWilayaController implements Initializable {


    private ObservableList<Wilaya> wilayasObservableList  = FXCollections.observableArrayList(Wilaya.wilayas);

    @FXML
    private ListView<Wilaya> wilayasListView;





    static class Cell extends ListCell<Wilaya> {
        HBox hBox = new HBox();
        Pane pane = new Pane();

        Label nomWilaya = new Label("");
        Label prixWilaya = new Label("");


        public Cell() {
            super();
            hBox.prefHeight(100);
            hBox.getChildren().addAll(nomWilaya, pane, prixWilaya);
            hBox.setHgrow(pane, Priority.ALWAYS);

        }

        public void updateItem(Wilaya wil, boolean empty) {
            super.updateItem(wil, empty);
            setText(null);
            setGraphic(null);

            if (wil != null && !empty) {
                nomWilaya.setText(wil.getNom());
                prixWilaya.setText(String.valueOf(wil.getPrixM()));
                setGraphic(hBox);


            }
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        wilayasListView.setItems(wilayasObservableList);
        wilayasListView.setCellFactory(param -> new Cell(){
        });


    }
}
