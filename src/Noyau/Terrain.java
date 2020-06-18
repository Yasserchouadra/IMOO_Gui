package Noyau;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Terrain extends Bien {

    private StatutJuridique stat;
    private int nbrFacade;



    public Terrain(Transaction tr, Proprietaire prop) throws InputMismatchException,NegativeException {
        Scanner intSc = new Scanner(System.in); // Scanner for integers floats and the rest numbers
        Scanner sc = new Scanner(System.in);// for strings

        nbBiens++;
        this.matricule = nbBiens;
        System.out.println("Matricule : " + this.matricule);

        System.out.println("Adresse: ");
        this.addresse = sc.nextLine();

        System.out.println("Wilaya :(le code de la wilaya):");
        this.wilaya = Wilaya.codeToWilaya(intSc.nextInt());

        System.out.println("Superficie (m\u00B2):");
        superficie = intSc.nextFloat();
        if (this.superficie < 0) {
            throw new NegativeException();
        }

        System.out.println("Nombre de Façades:");
        this.nbrFacade = intSc.nextInt();
        if (this.nbrFacade < 0) {
            throw new NegativeException();
        }

        System.out.println("Prix intial: ");
        this.prixInit = intSc.nextDouble();
        if (this.prixInit < 0) {
            throw new NegativeException();
        }

        System.out.println("Negociable: (o/n) o:oui, n:non: ");
        char reponse = sc.next().charAt(0); // pour voir si le prix est negociable ou non
        this.negociable = textToBool(reponse);


        Scanner sc2 = new Scanner(System.in);
        System.out.println("Votre Descriptioon du Bien:");
        this.description = sc2.nextLine();
        this.stat = typeStatut();


        this.dateAjout = LocalDate.now();

    }


    @Override
    public void afficherCourt() {
        System.out.println(">Terrain  * " + this.transaction + " de :"+prop.getNom());
        super.afficherCourt();
        if (transaction == Transaction.echange){
            System.out.println("  >Prix final(biens dans la meme wilaya) : "+calculPrix(this.transaction, true)+"  DA");
            System.out.println("  >Prix final : "+calculPrix(this.transaction, false)+"  DA");
        }
        else System.out.println("  >Prix final : "+calculPrix(this.transaction, true)+"  DA");

    }




    public Terrain(int matricule, String addresse, float superficie, Proprietaire prop,
                   Transaction transaction, double prixInit,
                   boolean negociable, String description, LocalDate dateAjout,
                   Wilaya wilaya, String photo, StatutJuridique stat, int nbrFacade)

    {
        super(matricule, addresse, superficie, prop, transaction, prixInit, negociable, description, dateAjout, wilaya, photo);
        this.stat = stat;
        this.nbrFacade = nbrFacade;
    }
    public Terrain(int matricule, String addresse, float superficie, Proprietaire prop,
                   Transaction transaction, double prixInit,
                   boolean negociable, String description, String dateAjout,
                   Wilaya wilaya, String photo, StatutJuridique stat, int nbrFacade)

    {
        super(matricule, addresse, superficie, prop, transaction, prixInit, negociable, description, dateAjout, wilaya, photo);
        this.stat = stat;
        this.nbrFacade = nbrFacade;
    }


    private StatutJuridique typeStatut() throws InputMismatchException {
        char choix;
        Scanner lect = new Scanner(System.in);
        System.out.println("Donner le Statut Juridique");
        System.out.println("1 - Livret Foncier");
        System.out.println("2 - Timbr\u00e9");
        choix = lect.next().charAt(0);


        switch (choix) {
            case '1':
                return StatutJuridique.livret;
            case '2':
                return StatutJuridique.timbre;
            default:
                throw new InputMismatchException();
        }
    }

    public void afficher() { //pour afficher un terrain.
        System.out.println("Terrain * "+this.transaction);
        System.out.println("Ajoute le :"+this.dateAjout.toString());
        super.afficher();
        System.out.println("Nombre d'etages :"+this.nbrFacade +" | Statut Juridique : "+this.stat);
        if (transaction == Transaction.echange){
            System.out.println("Prix final(biens dans la meme wilaya) : "+calculPrix(this.transaction, true)+"  DA");
            System.out.println("Prix final : "+calculPrix(this.transaction, false)+"  DA");
        }
        else System.out.println("Prix final : "+calculPrix(this.transaction, true)+"  DA");
        System.out.println("Bien de :"+this.prop.getPrenom()+" | "+this.prop.getPrenom()+" | "+"Tel :"+prop.getTel());
    }



    @Override
    public double calculPrix(Transaction tr, boolean mmWilaya) //methode calcul prix pour un terrain.
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
            if (nbrFacade > 1) { prixFinal = prixFinal + 0.005 * nbrFacade * prixInit ;}


            if (tr == Transaction.echange)
            {
                if (!(mmWilaya))
                    prixFinal = prixFinal + 0.0025 * prixInit;
            }
        }
        return prixFinal;
    }
}