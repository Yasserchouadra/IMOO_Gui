package Noyau;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Wilaya {
    private String nom;   // nom du département
    private double prixM; // prix du metre carré
    public static ArrayList<Wilaya> wilayas = new ArrayList<>();

    public Wilaya(){} // contructeur par défault

    public Wilaya(String nom, double prixM) {   // constructor
        this.nom = nom;
        this.prixM = prixM;
    }
    
    public void afficherWilaya(){
        System.out.println("Wilaya : "+this.nom+"_________________________________________Prix/m\u00B2 : "+ this.prixM +" DA");
    }


    public static ArrayList<Wilaya> ouvrirFichierPrix(String filename) {
        Path pathToFile = Paths.get(filename);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(";");


                Wilaya wil = new Wilaya(attributes[0],Double.parseDouble(attributes[1]));
                // adding Wilaya into ArrayList
                wilayas.add(wil);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return wilayas;

    }

    public static void afficherListePrix() {
        int i = 1;
        for (Wilaya wil:wilayas) {
            System.out.print(i+ ") ");
            i++;
            wil.afficherWilaya();
        }
    }



    public static Wilaya codeToWilaya(int matriculeWilaya){
        return wilayas.get(matriculeWilaya-1);
    }

    public static Wilaya stringToWilaya(String nomWilaya)   {

        for (Wilaya w:wilayas
             ) {
            if (w.nom.equals(nomWilaya))
                return w;

        }
        return null;
    }

    // setters and getters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixM() {
        return prixM;
    }
    public void setPrixM(double prixM) {
        this.prixM = prixM;
    }
}
