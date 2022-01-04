package main.model;
import main.view.NouvellePartie;

import java.io.*;
import java.util.Date;

/**
 * Cette classe correspond à la Sauvegarde, elle est sauvegardée dans un fichier
 */
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
    private boolean isDead;

    /**
     * Constructeur de SauvegardePartie
     * @param nomJoueur le nom du joueur
     * @param avatar le nom de l'avatar
     * @param tempsJeu le temps de jeu
     * @param isDead vrai si l'avatar est mort
     * @throws IOException
     */
    public SauvegardePartie(String nomJoueur, Avatar avatar, long tempsJeu, boolean isDead) throws IOException {
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
        this.isDead = isDead;

        long millis = System.currentTimeMillis();
        this.dateFinSession = new Date(millis);
        writeSave();

    }

    /**
     * Constructeur de SauvegardePartie qui permet de charger une partie
     * @param nom le nom du fichier de sauvegarde
     */
    public SauvegardePartie(String nom) {
        try {
            readSave(nom);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serialize cette classe dans un fichier
     * @throws IOException
     */
    private void writeSave() throws IOException {
        String filename = nomJoueur + "-" + nomAvatar + ".save";
        FileOutputStream file = new FileOutputStream("./Code/resources/saves/" + filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.close();
        file.close();
        System.out.println("La partie a bien été sauvegardée");
    }


    /**
     * Permet de lire une sauvegarde pour charger une partie
     * @param nom le nom de la sauvegarde
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readSave(String nom) throws IOException, ClassNotFoundException {

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
        this.isDead = save.isDead;
        NouvellePartie.difficulty = save.difficulty;

        in.close();
        file.close();

    }

    /**
     * Crée un avatar à partir d'une sauvegarde
     * @param nom le nom de la sauvegarde
     * @return un Avatar
     */
    public Avatar creerAvatar(String nom) {
        try {
            readSave(nom);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Avatar(typeAvatar, nomAvatar, santeAvatar, bonheurAvatar, nourritureAvatar, energieAvatar, hygieneAvatar, divertissementAvatar);
    }

    /**
     * Permet de récupérer le temps qui s'est écoulé depuis la dernière connexion
     * @return le temps qui s'est écoulé depuis la dernière connexion
     */
    public long getTimeSinceLastConnexion(){
        long millis = System.currentTimeMillis();
        Date current = new Date(millis);

        return (current.getTime() - this.dateFinSession.getTime());
    }

    /**
     * Le getter de tempsJeu
     * @return tempsJeu
     */
    public long getTempsJeu() {
        return this.tempsJeu;
    }

    /**
     * Le getter de nomJoueur
     * @return nomJoueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * Le getter de isDead
     * @return isDead
     */
    public boolean isDead() {
        return isDead;
    }
}
