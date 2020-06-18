package Noyau;
import java.time.LocalDate;


public abstract class Bien implements Comparable<Bien>
{


    protected int matricule;// matricule du bien
    protected String addresse;
    protected float superficie;

    protected Proprietaire prop;
    protected double prixInit;
    protected double prixFinal;
    protected boolean negociable;
    protected String description;
    protected LocalDate dateAjout;
    protected Wilaya wilaya;
    protected String photo;
    protected Transaction transaction;
    public static int nbBiens;

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    @Override
    public int compareTo(Bien o) {
        return -this.dateAjout.compareTo(o.getDateAjout());
    }

    public boolean equals(Bien other) {
        return this.dateAjout.equals((other).dateAjout) && this.addresse.equals(other.addresse) && this.wilaya.equals(other.wilaya);
    }

    public Bien(){
    }

    public Bien(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction, double prixInit,
                boolean negociable, String description, LocalDate dateAjout, Wilaya wilaya, String photo) {

        this.matricule = matricule;
        this.addresse = addresse;
        this.superficie = superficie;
        this.prop = prop;
        this.prixInit = prixInit;
        this.negociable = negociable;
        this.description = description;
        this.dateAjout = dateAjout;
        this.wilaya = wilaya;
        this.photo = photo;
        this.transaction = transaction;
        prop.ajouterBien(this);

    }
    public Bien(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction, double prixInit,
                boolean negociable, String description, String dateAjout, Wilaya wilaya, String photo) {

        this.matricule = matricule;
        this.addresse = addresse;
        this.superficie = superficie;
        this.prop = prop;
        this.prixInit = prixInit;
        this.negociable = negociable;
        this.description = description;
        this.dateAjout = LocalDate.parse(dateAjout);
        this.wilaya = wilaya;
        this.photo = photo;
        this.transaction = transaction;
        prop.ajouterBien(this);
    }


    public void afficher(){

        System.out.println("Code :"+matricule);
        System.out.println("Adresse :"+addresse);
        System.out.println("Wilaya : "+wilaya.getNom());
        System.out.println("Superficie :"+superficie+" m\u00B2");
        System.out.println("Description: "+description);
        System.out.println("Negociable :"+boolToText(negociable));

    }

    public void afficherCourt()
    // affichage sans detail
    {
        System.out.println("Ajouté le :"+dateAjout);
        System.out.println("  >Code : "+matricule+" | Wilaya :"+wilaya.getNom()+" | superficie : "+superficie+" m\u00B2");

    }


    public String boolToText(boolean bool){
        /** dans cette fonction elle prends en parametre un booll�en
         et retourne "OUI" si il est vrai "NON" s'il est faux */
        if (bool)
            return "Oui";
        else
            return "Non";
    }

    public abstract double calculPrix(Transaction tr, boolean mmWilaya);

    public static  boolean textToBool(String rep){
        rep.toLowerCase();
        return rep == "oui" || rep == "o";
    }
    public static boolean textToBool(char rep){
        return rep == 'o' || rep == 'O';
    }


    public static String boolToString(boolean test){
        if (test)
            return "Oui";
        else return "Non";

    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public Proprietaire getProp() {
        return prop;
    }

    public void setProp(Proprietaire prop) {
        this.prop = prop;
    }

    public double getPrixInit() {
        return prixInit;
    }

    public void setPrixInit(double prixInit) {
        this.prixInit = prixInit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wilaya getWilaya() {
        return wilaya;
    }

    public void setWilaya(Wilaya wilaya) {
        this.wilaya = wilaya;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean isNegociable() {
        return negociable;
    }
}

