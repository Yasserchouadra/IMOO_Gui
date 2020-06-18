package Noyau;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Proprietaire
{
    private int matricule;
    private String nom;
    private String prenom;
    private String adrMail;
    private String tel;
    private String adresse;
    private ArrayList<Bien> biens = new ArrayList<Bien>();
    static int nbProps=0;
    public static ArrayList<Proprietaire> proprietaires = new ArrayList<Proprietaire>();


    public Proprietaire(){

        nbProps++;
        this.matricule = nbProps;
        Scanner sc = new Scanner(System.in);
        Scanner intSc = new Scanner(System.in);
        System.out.println("Nom : ");
        this.nom = sc.nextLine();
        System.out.println("Prenom : ");
        this.prenom = sc.nextLine();
        System.out.println("Adresse: ");
        this.adresse = sc.nextLine();
        System.out.println("Adresse mail : ");
        this.adrMail = sc.nextLine();
        System.out.println("Telephone : ");
        this.tel = sc.nextLine();
    }

    public Proprietaire(int matricule,String nom, String prenom, String adrMail, String tel, String adresse) {
        nbProps++;
        this.matricule = nbProps;
        this.nom = nom;
        this.prenom = prenom;
        this.adrMail = adrMail;
        this.tel = tel;
        this.adresse = adresse;

        proprietaires.add(this);
    }


    public void afficher_biens(){

        System.out.println("les biens de "+this.nom+" "+prenom);
        if(!(biens.isEmpty())) {
            for (Bien bien: biens
                 ) {
                bien.afficher();
            }
        }
        else
            System.out.println(RED+"Liste des biens vide"+RESET);
    }



    public void afficher(){
        System.out.println("> Nom : "+this.nom+" | Prenom : "+this.prenom);
        System.out.println("   > Adresse e-mail : "+this.adrMail);
        System.out.println("   > Nombre de biens : "+ this.biens.size());
        
    }

    public int getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String presnom) {
        this.prenom = presnom;
    }
    public String getadrMail() {
        return adrMail;
    }
    public void setadrMail(String adrMail) {
        this.adrMail = adrMail;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Bien> getBiens() {
        return biens;
    }

    public void ajouterBien(Bien b){
        biens.add(b);
    }

    public void supprimerBien(Bien bien){
        biens.remove(bien);
    }





    /** Gestion des fichier */
    public static void ouvrirFicherProprietaires(String filename)
    /** cette procedure sert à charger les données depuis le fichier "filename" des proprétaires

     */

    {
        Path pathToFile = Paths.get(filename);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            // read the first line from the text file
            String line = br.readLine();
            line = br.readLine();


            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(";");

                Proprietaire prop = new Proprietaire(Integer.parseInt(attributes[0]),attributes[1], attributes[2],  attributes[3],  attributes[4], attributes[5]);
                // adding un proprietaire into ArrayList
                //proprietaires.add(prop);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }



    public void ajouterAuFichier() throws IOException {
        FileWriter csvWriter = new FileWriter("proprietaires.csv", true);

        csvWriter.append(String.valueOf(this.getMatricule())+";");
        csvWriter.append(this.getNom()+";");
        csvWriter.append(this.getPrenom()+";");
        csvWriter.append(this.getadrMail()+";");
        csvWriter.append(this.getTel()+";");
        csvWriter.append(this.getAdresse()+";");
        csvWriter.append("\n");


    }

    public static void chargerFicherProprietaire() throws IOException {

        FileWriter csvWriter = new FileWriter("Proprietaire.proprietaires.csv");

        csvWriter.append("matricule;nom;prenom;adrmail;telephone;adresse;\n");

        for (Proprietaire prop:Proprietaire.proprietaires
        ) {
            csvWriter.append(String.valueOf(prop.getMatricule())+";");
            csvWriter.append(prop.getNom()+";");
            csvWriter.append(prop.getPrenom()+";");
            csvWriter.append(prop.getadrMail()+";");
            csvWriter.append(prop.getTel()+";");
            csvWriter.append(prop.getAdresse()+";");
            csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();

    }

    public static Proprietaire stringToProprietaire(String nomPrenomTextField){
        for (Proprietaire prop:proprietaires
             ) {if ((prop.getNom()+" "+prop.getPrenom()).equals(nomPrenomTextField)) {
                    return prop;
                 }
        }
        return null;
    }



    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

}
