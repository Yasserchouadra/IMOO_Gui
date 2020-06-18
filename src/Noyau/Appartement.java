package Noyau;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Appartement extends Habitable {

    private int etage;
    private boolean simplexe;
    private boolean ascenseur;


    public Appartement(Transaction tr, Proprietaire prop ) throws InputMismatchException, NegativeException {
        Scanner intSc = new Scanner(System.in); // Scanner for integers floats and the rest numbers
        Scanner sc = new Scanner(System.in);// for strings
            System.out.println("Saisir les informations du Bien: ");

            nbBiens++;
            this.matricule = nbBiens;
            System.out.println("Matricule : "+nbBiens+1);

            System.out.println("Adresse: ");
            this.addresse = sc.nextLine();

            System.out.println("Wilaya :(le code de la wilaya):");
            this.wilaya = Wilaya.codeToWilaya(intSc.nextInt());

            System.out.println("Superficie (m\u00B2):");
            this.superficie = intSc.nextFloat();
            if (this.superficie < 0) {throw  new NegativeException();}

            System.out.println("Nombre de pieces:");
            //this.nbPieces = intSc.nextInt();
            //  int a=intSc.nextInt();
            //if(a<0){throw new NegativeException();}
            //this.nbPieces=a;

            this.nbPieces = intSc.nextInt();
            if (this.nbPieces < 0) {throw  new NegativeException();}

            System.out.println("Etage : ");
            this.etage = intSc.nextInt();
            if (this.etage < 0) {throw  new NegativeException();}


            System.out.println("Ascenseur : (o/n) o:oui, n:non: ");
            char reponse = sc.next().charAt(0);// pour voir si l'immeuble a un ascenseur?
            this.ascenseur = textToBool(reponse);

            System.out.println("Simplexe : (o/n) o:oui, n:non: ");
            reponse = sc.next().charAt(0);// pour voir si l'appartement est simplexe?
            this.simplexe = textToBool(reponse);

            System.out.println("Meuble : (o/n) o:oui, n:non: ");
            reponse = sc.next().charAt(0);// pour voir si l'appartement est meublé ou pas
            this.meuble = textToBool(reponse);

            System.out.println("Prix intial: ");
            this.prixInit = intSc.nextDouble();
            if (this.prixInit < 0) {throw  new NegativeException();}


            System.out.println("Negociable: (o/n) o:oui, n:non: ");
            reponse = sc.next().charAt(0); // pour voir si le prix est negociable ou non
            this.negociable = textToBool(reponse);


            System.out.println("Votre Descriptioon du Bien:");
            Scanner sc2 = new Scanner(System.in);
            this.description = sc2.nextLine();

            this.transaction = tr;
            this.prop = prop;
            this.dateAjout = LocalDate.now();


     }

    public Appartement(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction , double prixInit,
                       boolean negociable, String description, LocalDate dateAjout, Wilaya wilaya, String photo, int nbPieces,
                       boolean meuble, int etage, boolean simplexe, boolean ascenseur) {

        super(matricule, addresse, superficie, prop,transaction,  prixInit, negociable, description, dateAjout, wilaya, photo, nbPieces, meuble);
        this.etage = etage;
        this.simplexe = simplexe;
        this.ascenseur = ascenseur;

    }

    public Appartement(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction , double prixInit,
                       boolean negociable, String description, String dateAjout, Wilaya wilaya, String photo, int nbPieces,
                       boolean meuble, int etage, boolean simplexe, boolean ascenseur) {

        super(matricule, addresse, superficie, prop,transaction,  prixInit, negociable, description, dateAjout, wilaya, photo, nbPieces, meuble);
        this.etage = etage;
        this.simplexe = simplexe;
        this.ascenseur = ascenseur;
    }


    @Override
    public void afficher() {

        System.out.println("Appartement  * " + this.transaction);
        System.out.println("Ajoute le :"+this.dateAjout.toString());
        super.afficher();
        System.out.println("Etage: "+etage+" eme Etage, \nOptions:\n1) Simplexe:"+boolToText(simplexe)+"\n2) Ascenseur : "+boolToText(ascenseur));

        if (transaction == Transaction.echange){
            System.out.println("Prix final(biens dans la meme wilaya) : "+calculPrix(this.transaction, true)+"  DA");
            System.out.println("Prix final : "+calculPrix(this.transaction, false)+"  DA");
        }
        else System.out.println("Prix final : "+calculPrix(this.transaction, true)+"  DA");

        System.out.println("Bien de :"+this.prop.getPrenom()+" | "+this.prop.getPrenom()+" | "+"Tel :"+prop.getTel());
    }


    @Override
    public void afficherCourt() {
        System.out.println("> Appartement  * " + this.transaction + " de :"+prop.getNom());
        super.afficherCourt();
        if (transaction == Transaction.echange){
            System.out.println("  >Prix final(biens dans la meme wilaya) : "+calculPrix(this.transaction, true)+"  DA");
            System.out.println("  >Prix final : "+calculPrix(this.transaction, false)+"  DA");
        }
        else System.out.println("  >Prix final : "+calculPrix(this.transaction, true)+"  DA");

    }

    @Override
    public double calculPrix(Transaction tr, boolean mmWilaya) //pour appartement.
    {
        if (tr == Transaction.vente || tr == Transaction.echange )
        {
            if (prixInit < 5000000)
            {
                if (wilaya.getPrixM() < 50000) { prixFinal = prixInit * 1.03;}
                else prixFinal = prixInit * 1.035;
            }
            else
            {
                if (prixInit > 15000000)
                {
                    if (wilaya.getPrixM() < 70000) {prixFinal = prixInit * 1.01;}
                    else prixFinal = prixInit * 1.02;
                }
                else
                {   //le prix entre 5 et 15 millions
                    if (wilaya.getPrixM() < 50000){ prixFinal = prixInit * 1.02;}
                    else prixFinal = prixInit * 1.025;
                }
            }
            //je vais ajouter la partie qui est speciale pour un appartement
            if (etage <= 2) { prixFinal = prixFinal + 50000 ;}

            if (tr == Transaction.echange)
            {
                    if (!(mmWilaya))
                    prixFinal = prixFinal + 0.0025 * prixInit;
            }
        }
        else //c'est une LOCATION
        {
            if (superficie < 60)
            {
                if (wilaya.getPrixM() < 50000) {prixFinal = prixInit * 1.01;}
                else prixFinal = prixInit * 1.015;
            }
            else
            {
                if (superficie > 150)
                {
                    if (wilaya.getPrixM() < 50000) {prixFinal = prixInit * 1.03;}
                    else prixFinal = prixInit * 1.035;
                }
                else // superficie entre 60 et 150
                {
                    if (wilaya.getPrixM() < 50000) {prixFinal = prixInit * 1.02;}
                    else prixFinal = prixInit * 1.025;
                }
            }
            //la partie d'une appartement.
            if (etage <= 2) { prixFinal = prixFinal + 5000;}
            if (etage >= 6 && !ascenseur) {prixFinal = prixFinal - 500 * superficie ;}
        }
        return prixFinal;

    }


    public int getEtage() {
        return etage;
    }

    public boolean isSimplexe() {
        return simplexe;
    }

    public boolean isAscenseur() {
        return ascenseur;
    }
}
