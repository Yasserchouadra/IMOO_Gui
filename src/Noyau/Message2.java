package Noyau;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Message;
import java.util.Properties;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Message2 {
    private String nomExpiditeur;
    private String email;
    private String textMessage;
    public static ArrayList<Message2> messages =  new ArrayList<>();

    public Message2(String nomExpiditeur, String email, String textMessage, Bien b) throws MessagingException {
        this.nomExpiditeur = nomExpiditeur;
        this.email = email;
        this.textMessage = textMessage;
        messages.add(this);
        sendMail("ic_benaziza@esi.dz",this.nomExpiditeur,  this.textMessage, b);

    }
    public Message2(String nomExpiditeur, String email, String textMessage) throws MessagingException {
        this.nomExpiditeur = nomExpiditeur;
        this.email = email;
        this.textMessage = textMessage;
        messages.add(this);

    }

    public Message2(Bien b) throws MessagingException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nouveau message:");
        System.out.print("Nom");
        this.nomExpiditeur = sc.nextLine();
        System.out.print("E-mail:");
        this.email = sc.nextLine();
        System.out.println("Votre message: ");
        this.textMessage = sc.nextLine();

        messages.add(this);

        sendMail("ic_benaziza@esi.dz",this.nomExpiditeur,  this.textMessage, b);
        sendMail("io_benakmoum@esi.dz", this.nomExpiditeur, this.textMessage, b);
    }



    public void afficher(){
        System.out.println("Nom: "+this.nomExpiditeur);
        System.out.println("Email: "+this.email);
        System.out.println("--------");
        System.out.println(this.textMessage);
        System.out.println("--------");
    }

    public static ArrayList<Message2> ouvrirFichierPrix(String filename)  {
        Path pathToFile = Paths.get(filename);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            // read the first line from the text file
            String line = br.readLine();
            line=br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(";");


                Message2 mess = new Message2(attributes[0],attributes[1], attributes[2]);
                // adding Message2 into ArrayList
                messages.add(mess);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException | MessagingException ioe) {
            ioe.printStackTrace();
        }

        return messages;

    }

    public static void chargerFichierMessage() throws IOException {
        FileWriter csvWriter = new FileWriter("messages.csv");

        csvWriter.append("nom;mail;message\n");

        for (Message2 mess :messages
        ) {
            csvWriter.append(mess.nomExpiditeur+";");
            csvWriter.append(mess.email+";");
            csvWriter.append(mess.textMessage+";");
            csvWriter.append("\n");

        }
        csvWriter.flush();
        csvWriter.close();
    }



    public static void sendMail(String recepient,String sender, String textMessage, Bien b) throws MessagingException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Preparing to send email");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "ic_benaziza@esi.dz";
        String password = "DRchemous2015";


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMesssage(session, myAccountEmail, recepient,sender,  textMessage, b);
        Transport.send(message);
        System.out.println("Message envoy\u00E9 avec succ\u00E8s");

    }

    private static Message prepareMesssage(Session session, String myAccountEmail, String recepient ,String sender,  String textMessage, Bien b){
        try {

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(myAccountEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            mess.setSubject("E-mail d'un client de l'agence");
            mess.setText(" \n Je suis le client "+sender+"  "+"  \nLe bien "+b.getClass().getSimpleName()+" \na l'adresse  "+b.addresse+"  "+" \nWilaya:" +b.getWilaya().getNom()+" \n\n  "+textMessage);
            return mess;
        }catch (Exception ex){
            System.out.println("");
            Logger.getLogger(Message2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public String getNomExpiditeur() {
        return nomExpiditeur;
    }
    public void setNomExpiditeur(String nomExpiditeur) {
        this.nomExpiditeur = nomExpiditeur;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return textMessage;
    }
    public void setMessage(String message) {
        textMessage = message;
    }

























}
