package sample;

import Noyau.Message2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBoiteController implements Initializable {

    private ObservableList<Message2> messagesObservableList = FXCollections.observableArrayList(Message2.messages);

    @FXML
    private ListView<Message2> messageListView = new ListView<>();

    public class MessageCell extends ListCell<Message2> {

        HBox hBox = new HBox();
        Pane pane = new Pane();

        Label senderNameLabel = new Label("");

        Label messageLabel = new Label("");

        public MessageCell() {
            super();

            hBox.getChildren().addAll(senderNameLabel, messageLabel, pane);
            hBox.setHgrow(pane, Priority.ALWAYS);
        }


        public void updateItem(Message2 message, boolean empty) {
            super.updateItem(message, empty);
            setText(null);
            setGraphic(null);

            if (message != null && !empty) {
                senderNameLabel.setText(message.getNomExpiditeur() + "  ");
                messageLabel.setText(" " + message.getMessage());
                setGraphic(hBox);


            }
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        messageListView.setItems(messagesObservableList);
        messageListView.setCellFactory(param -> new MessageBoiteController.MessageCell());


    }
}
