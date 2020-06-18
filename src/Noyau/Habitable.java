package Noyau;

import java.time.LocalDate;

public abstract class Habitable extends Bien
{
    protected int nbPieces;
    protected boolean meuble;

   public Habitable(){}

    public Habitable(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction, double prixInit,
                     boolean negociable, String description, LocalDate dateAjout, Wilaya wilaya, String photo,
                     int nbPieces, boolean meuble) {

        super(matricule, addresse, superficie, prop,transaction,  prixInit, negociable, description, dateAjout, wilaya, photo );
        this.nbPieces = nbPieces;
        this.meuble = meuble;
    }
    public Habitable(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction, double prixInit,
                     boolean negociable, String description, String dateAjout, Wilaya wilaya, String photo,
                     int nbPieces, boolean meuble) {

        super(matricule, addresse, superficie, prop,transaction,  prixInit, negociable, description, dateAjout, wilaya, photo );
        this.nbPieces = nbPieces;
        this.meuble = meuble;
    }
}
