package sample;

import Noyau.Agence;
import Noyau.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;



public class TableauDeBordController implements Initializable {

    @FXML
    private PieChart pieChart = new PieChart();


    @FXML
    private PieChart pieChart2 = new PieChart();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PieChart.Data slice1 = new PieChart.Data("Maison", Agence.getNbrBiensSelonType("Maison"));
        PieChart.Data slice2 = new PieChart.Data("Appartement", Agence.getNbrBiensSelonType("Appartement"));
        //PieChart.Data slice3 = new PieChart.Data("Terrain", Agence.getNbrBiensSelonType("Terrain"));


        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
       // pieChart.getData().add(slice3);


        PieChart.Data slice1P2 = new PieChart.Data("Vente", Agence.getNbrBiensSelonTransaction(Transaction.vente));
        PieChart.Data slice2P2 = new PieChart.Data("Location", Agence.getNbrBiensSelonTransaction(Transaction.location));
        PieChart.Data slice3P2 = new PieChart.Data("Echange", Agence.getNbrBiensSelonTransaction(Transaction.echange));

        pieChart2.getData().add(slice1P2);
        pieChart2.getData().add(slice2P2);
        pieChart2.getData().add(slice3P2);



    }
}
