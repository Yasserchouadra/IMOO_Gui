package Noyau;
import java.util.Scanner;

public class Admin {
    private String userID;
    private String password;


    public Admin(String userID, String password){      // constructor
        this.userID = userID;
        this.password = password;

    }



    public boolean identification(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Username: "); String user = sc.next();
        System.out.print("Mot de passe: "); String psswd = sc.next();


        if (user.equalsIgnoreCase(this.userID) && psswd.equalsIgnoreCase(this.password)) {
            Agence.access = true;
            return true;

        }
        else return false;
    }

    public boolean identification(String user,String passwd)
    {
        if (user.equalsIgnoreCase(this.userID) && passwd.equalsIgnoreCase(this.password))
            return true;
        else return false;

    }


    public void afficher_renseignement(){
        System.out.println("Nom admin :"+userID);

    }

    public String getUserID() {
        return userID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }


}
