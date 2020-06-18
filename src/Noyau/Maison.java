package Noyau;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Maison extends Habitable
{
    private int nbEtages;
    private boolean garage;
    private float piscine;
    private float jardin;


    public Maison(Transaction tr, Proprietaire  prop) throws InputMismatchException, NegativeException {
        Scanner intSc = new Scanner(System.in); // Scanner for integers floats and the rest numbers
        Scanner sc = new Scanner(System.in);// for strings


            System.out.println("Saisir les informations du Bien: ");
            nbBiens++;
            this.matricule = nbBiens;
            System.out.println("Matricule : " + this.matricule);
            System.out.println("Adresse: ");
            this.addresse = sc.nextLine();

            System.out.println("Wilaya :(le code de la wilaya):");
            this.wilaya = Wilaya.codeToWilaya(intSc.nextInt());

            System.out.println("Superficie (m\u00B2):");
            this.superficie = intSc.nextFloat();
            if (this.superficie < 0) {throw  new NegativeException();}

            System.out.println("Nombre de pieces:");
            this.nbPieces = intSc.nextInt();
            if (this.nbPieces < 0) {throw  new NegativeException();}

            System.out.println("Nombre d'etages:");
            this.nbEtages = intSc.nextInt();
            if (this.nbEtages < 0) {throw  new NegativeException();}

            System.out.println("Garage : (o/n) o:oui, n:non: ");
            char reponse = sc.next().charAt(0);// pour voir si l'immeuble a un garage?
            this.garage = textToBool(reponse);


            System.out.println("Meuble : (o/n) o:oui, n:non: ");
            reponse = sc.next().charAt(0);// pour voir si l'appartement est meublé ou pas
            this.meuble = textToBool(reponse);

            System.out.println("Piscine : (0 si non, Donner la surface si OUI): ");
            this.piscine = intSc.nextFloat();

            System.out.println("Jardin : (0 si non, Donner la surface si OUI): ");
            this.jardin = intSc.nextFloat();
            if (this.piscine + this.jardin > (this.superficie)/2) throw new InputMismatchException();

            System.out.println("Prix intial: ");
            this.prixInit = intSc.nextDouble();
            if (this.prixInit < 0) throw  new NegativeException();

            System.out.println("Negociable: (o/n) o:oui, n:non: ");
            reponse = sc.next().charAt(0); // pour voir si le prix est negociable ou non
            this.negociable = textToBool(reponse);


            Scanner sc2 = new Scanner(System.in);
            System.out.println("Votre Descriptioon du Bien:");
            this.description = sc2.nextLine();

            this.transaction = tr;
            this.prop = prop;
            dateAjout = LocalDate.now();



    }

    public Maison(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction, double prix_init,
                  boolean negociable, String description, LocalDate dateAjout, Wilaya wialaya, String photo, int nbPieces,
                  boolean meuble, int nbEtages, boolean garage, float piscine, float jardin) throws InputMismatchException


    {
        super(matricule, addresse, superficie, prop, transaction, prix_init, negociable, description, dateAjout,wialaya, photo, nbPieces, meuble);
        this.nbEtages = nbEtages;
        this.garage = garage;

        this.piscine = piscine;
        this.jardin = jardin;
        if (this.piscine + this.jardin > (this.superficie)/2) throw new InputMismatchException();


    }
    public Maison(int matricule, String addresse, float superficie, Proprietaire prop, Transaction transaction, double prix_init,
                  boolean negociable, String description, String dateAjout, Wilaya wialaya, String photo, int nbPieces,
                  boolean meuble, int nbEtages, boolean garage, float piscine, float jardin)


    {
        super(matricule, addresse, superficie, prop, transaction, prix_init, negociable, description, dateAjout,wialaya, photo, nbPieces, meuble);
        this.nbEtages = nbEtages;
        this.garage = garage;
        this.piscine = piscine;
        this.jardin = jardin;

    }






    @Override
    public void afficher() {
        System.out.println("Maison * "+this.transaction);
        System.out.println("Ajoute le :"+this.dateAjout.toString());
        super.afficher();
        System.out.println("Nombre d'etages :"+this.nbEtages + " | Jardin "+jardin+" m2"+" | Piscine : "+this.piscine+"m2");
        if (transaction == Transaction.echange){
            System.out.println("Prix final(biens dans la meme wilaya) : "+calculPrix(this.transaction, true)+"  DA");
            System.out.println("Prix final : "+calculPrix(this.transaction, false)+"  DA");
        }
        else System.out.println("Prix final : "+calculPrix(this.transaction, true)+"  DA");
        System.out.println("Bien de :"+this.prop.getPrenom()+" | "+this.prop.getPrenom()+" | "+"Tel :"+prop.getTel());

    }

    public void afficherCourt() {
        System.out.println(">Maison  * " + this.transaction + " de :"+prop.getNom());
        super.afficherCourt();
        if (transaction == Transaction.echange){
            System.out.println("  >Prix final(biens dans la meme wilaya) : "+calculPrix(this.transaction, true)+"  DA");
            System.out.println("  >Prix final : "+calculPrix(this.transaction, false)+"  DA");
        }
        else System.out.println("  >Prix final : "+calculPrix(this.transaction, true)+"  DA");

    }




    @Override
    public double calculPrix(Transaction tr, boolean mmWilaya) //methode calcul prix pour une maison
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
            //je vais ajouter la partie qui est speciale pour une maison
            if (garage || piscine != 0 || jardin != 0) {prixFinal = prixFinal + 0.005 * prixInit;}

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

            if (piscine > 0)
            {
                prixFinal = prixFinal + 50000;
            }
        }
    return prixFinal;

    }

}
