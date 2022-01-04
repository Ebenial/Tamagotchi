package main.model;
import main.view.NouvellePartie;
import main.view.Sauvegardes;

import java.io.*;
import java.util.Date;


public class SauvegardePartie implements Serializable {
    private Date dateFinSession;
    private long tempsJeu;
    private String nomJoueur;
    private String nomAvatar;
    private String typeAvatar;
    private int santeAvatar;
    private int nourritureAvatar;
    private int energieAvatar;
    private int hygieneAvatar;
    private int divertissementAvatar;
    private int bonheurAvatar;
    private String difficulty;
    private Sauvegardes sauvegardes;

    public SauvegardePartie(String nomJoueur, Avatar avatar, long tempsJeu) throws IOException {
        this.nomJoueur = nomJoueur;
        this.nomAvatar = avatar.getNom();
        this.typeAvatar = avatar.getType();
        this.tempsJeu = tempsJeu;
        this.santeAvatar = avatar.getSante();
        this.nourritureAvatar = avatar.getNourriture();
        this.energieAvatar = avatar.getEnergie();
        this.hygieneAvatar = avatar.getHygiene();
        this.divertissementAvatar = avatar.getDivertissement();
        this.bonheurAvatar = avatar.getBonheur();
        this.difficulty = NouvellePartie.difficulty;

        long millis = System.currentTimeMillis();
        this.dateFinSession = new Date(millis);
        writeJSON();

    }

    public SauvegardePartie(String nom) {
        try {
            readJson(nom);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeJSON() throws IOException {
        String filename = nomJoueur + "-" + nomAvatar + ".json";
        FileOutputStream file = new FileOutputStream("./Code/resources/saves/" + filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.close();
        file.close();
        System.out.println("Object has been serialized");
    }



    private void readJson(String nom) throws IOException, ClassNotFoundException {

        FileInputStream file = new FileInputStream(nom);
        ObjectInputStream in = new ObjectInputStream(file);

        SauvegardePartie save = (SauvegardePartie) in.readObject();
        this.dateFinSession = save.dateFinSession;
        this.tempsJeu = save.tempsJeu;
        this.nomJoueur = save.nomJoueur;
        this.nomAvatar = save.nomAvatar;
        this.santeAvatar = save.santeAvatar;
        this.bonheurAvatar = save.bonheurAvatar;
        this.nourritureAvatar = save.nourritureAvatar;
        this.energieAvatar = save.energieAvatar;
        this.hygieneAvatar = save.hygieneAvatar;
        this.divertissementAvatar = save.divertissementAvatar;
        this.typeAvatar = save.typeAvatar;
        NouvellePartie.difficulty = save.difficulty;

        in.close();
        file.close();

    }

    public Avatar creerAvatar(String nom) {
        try {
            readJson(nom);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("typeAvatar : " + typeAvatar);
        System.out.println("nomAvatar : " + nomAvatar);
        System.out.println("santeAvatar : " + santeAvatar);
        System.out.println("bonheurAvatar : " + bonheurAvatar);
        System.out.println("nourritureAvatar : " + nourritureAvatar);
        System.out.println("energieAvatar : " + energieAvatar);
        System.out.println("hygieneAvatar : " + hygieneAvatar);
        System.out.println("divertissementAvatar : " + divertissementAvatar);
        return new Avatar(typeAvatar, nomAvatar, santeAvatar, bonheurAvatar, nourritureAvatar, energieAvatar, hygieneAvatar, divertissementAvatar);
    }

    public long getTimeSinceLastConnexion(){
        long millis = System.currentTimeMillis();
        Date current = new Date(millis);

        return (current.getTime() - this.dateFinSession.getTime());
    }

    public long getTempsJeu() {
        return this.tempsJeu;
    }

    public Date getDateFinSession() {
        return this.dateFinSession;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public String getNomAvatar() {
        return nomAvatar;
    }

    public String getTypeAvatar() {
        return typeAvatar;
    }

    public int getSanteAvatar() {
        return santeAvatar;
    }

    public int getNourritureAvatar() {
        return nourritureAvatar;
    }

    public int getEnergieAvatar() {
        return energieAvatar;
    }

    public int getHygieneAvatar() {
        return hygieneAvatar;
    }

    public int getDivertissementAvatar() {
        return divertissementAvatar;
    }

    public int getBonheurAvatar() {
        return bonheurAvatar;
    }
}
