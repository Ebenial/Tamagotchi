package main.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.model.*;
import main.util.CustomFont;
import main.util.LookAndFeel;

/**
 * Permet de modéliser la fenêtre du programme et de la paramétrer
 */
public class FenetrePrincipale extends JFrame{

    private Accueil accueil;
    private Jouer jouer;
    private NouvellePartie nouvellePartie;
    private BoucleJeu boucle;
    private Sauvegardes sauvegardes;
    private Options options;
    private Regles regles;
    private Environnement jardin;
    private Environnement cuisine;
    private Environnement chambre;
    private Environnement douche;
    private Environnement currentEnvironnement;
    private Jeu jeu;
    private CardLayout layout = new CardLayout();
    private boolean isInitialized = false;

    /**
     * Créé la fenêtre principale du jeu
     */
    public FenetrePrincipale(){

        LookAndFeel.initLookAndFeel();
        CustomFont.initFont();

        this.setTitle("Keneil - The best Tamagotchi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        int largeur = Toolkit.getDefaultToolkit().getScreenSize().width;
        int hauteur = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(new Dimension(largeur, hauteur)); //Prévu pour une résolution 1920*1080

        this.getContentPane().setLayout(layout);

        this.accueil = new Accueil(this);
        this.jouer = new Jouer(this);
        this.nouvellePartie = new NouvellePartie(this);
        this.options = new Options(this);
        this.regles = new Regles(this);
        this.sauvegardes = new Sauvegardes(this);

        this.add(accueil, "accueil");
        this.add(jouer, "jouer");
        this.add(nouvellePartie, "nouvellePartie");
        this.add(options, "options");
        this.add(regles, "regles");
        //this.add(sauvegardes, "sauvegardes");

        this.setVisible(true);
    }

    /**
     * Permet de quitter le programme après confirmation
     */
    public void actionQuitter(){
        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if(confirmation == JOptionPane.YES_OPTION){
            this.dispose();
            System.exit(0);
        }
    }    

    /**
     * Action lors du clic sur le bouton jouer, affiche un nouveau panneau
     */
    public void actionJouer(){
        this.layout.show(this.getContentPane(), "jouer");
    }

    /**
     * Affiche le panneau des règles
     */
    public void actionRegles(){
        this.layout.show(this.getContentPane(), "regles");
    }

    /**
     * Affiche le panneau des options
     */
    public void actionOptions(){
        this.layout.show(this.getContentPane(), "options");
    }

    /**
     * Affiche le panneau pour créer un nouvel avatar
     */
    public void actionNouvellePartie(){
        this.layout.show(this.getContentPane(), "nouvellePartie");
    }

    /**
     * Affiche le panneau qui répertorie les différentes sauvegardes existantes
     */
    public void actionContinuer(){

        this.layout.show(this.getContentPane(), "jouer");
        SauvegardePartie partie = new SauvegardePartie();
        this.jeu = new Jeu();

        this.jeu.setAvatar(partie.creerAvatar());
        this.jeu.setJoueur(new Joueur(partie.getNomJoueur()));

        System.out.println("TYPE AVATAR2 : " + this.jeu.getAvatar().getType());

        this.chambre = new Environnement("Chambre", this);
        this.douche = new Environnement("Douche", this);
        this.cuisine = new Environnement("Cuisine", this);
        this.jardin = new Environnement("Jardin", this);
        this.currentEnvironnement = chambre; // A MODIFIER !!!!!!!!!!!!!!!!!!!!

        this.add(chambre, "chambre");
        this.add(douche, "douche");
        this.add(cuisine, "cuisine");
        this.add(jardin, "jardin");

        this.layout.show(this.getContentPane(), "chambre");
        isInitialized = true;

    }

    /**
     * Affiche la panneau précédent le panneau en cours
     * @param s - le nom du panneau en cours (le bouton retour est présent sur plusieurs panneaux)
     */
    public void actionRetour(String s){

        switch (s) {
            case "Jouer":
            case "Options":
            case "Regles":
                this.layout.show(this.getContentPane(), "accueil");
                break;
            case "NouvellePartie":
            case "Sauvegardes":
                this.layout.show(this.getContentPane(), "jouer");
                break;
        }
    }

    /**
     * Sauvegarde la partie
     */
    public void actionSauvegarde() {
        try {
            new SauvegardePartie(this.jeu.getJoueur().getNom(), this.jeu.getAvatar(), this.jeu.getCompteur());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("je sauvegarde bien");
    }

    /**
     * Affiche le panneau correspondant à la chambre (Panneau de départ pour une nouvelle partie)
     */
    public void actionValider(){

        this.jeu = new Jeu(NouvellePartie.nomJoueur.getText(), NouvellePartie.nomAvatar.getText(), NouvellePartie.monChoix);

        this.chambre = new Environnement("Chambre", this);
        this.douche = new Environnement("Douche", this);
        this.cuisine = new Environnement("Cuisine", this);
        this.jardin = new Environnement("Jardin", this);
        this.currentEnvironnement = chambre; // A MODIFIER !!!!!!!!!!!!!!!!!!!!

        this.add(chambre, "chambre");
        this.add(douche, "douche");
        this.add(cuisine, "cuisine");
        this.add(jardin, "jardin");

        this.layout.show(this.getContentPane(), "chambre");
        isInitialized = true;
    }

    /**
     * Change le lieu dans lequel se trouve l'avatar
     * @param lieu - le lieu actuel de l'avatar
     * @param orientation - le côté vers lequel se dirige l'avatar (gauche ou droite)
     */
    public void actionChangementEnvironnement(String lieu, String orientation){

        if(orientation.equals("Gauche")){
            switch (lieu) {
                case "Chambre":
                    this.currentEnvironnement = douche;
                    this.getBoucle().updateAllStats();
                    this.layout.show(this.getContentPane(), "douche");
                    break;
                case "Douche":
                    this.currentEnvironnement = cuisine;;
                    this.getBoucle().updateAllStats();
                    this.layout.show(this.getContentPane(), "cuisine");
                    break;
                case "Cuisine":
                    this.currentEnvironnement = jardin;
                    this.getBoucle().updateAllStats();
                    this.layout.show(this.getContentPane(), "jardin");
                    break;
            }
        }else if(orientation.equals("Droite")){
            switch (lieu) {
                case "Chambre":
                case "Jardin":
                    this.currentEnvironnement = cuisine;
                    this.getBoucle().updateAllStats();
                    this.layout.show(this.getContentPane(), "cuisine");
                    break;
                case "Douche":
                    this.currentEnvironnement = chambre;
                    this.getBoucle().updateAllStats();
                    this.layout.show(this.getContentPane(), "chambre");
                    break;
                case "Cuisine":
                    this.currentEnvironnement = douche;
                    this.layout.show(this.getContentPane(), "douche");
                    this.getBoucle().updateAllStats();
                    break;
            }
        }
    }

    public void actionOptionsEnJeu() {
        System.out.println("coucou les amis");
        this.layout.show(this.getContentPane(), "options");
    }

    //GETTERS

    public Jeu getJeu(){
        return this.jeu;
    }

    public Environnement getCurrentEnvironnement() {
        return this.currentEnvironnement;
    }

    public boolean getIsInitialized() {
        return this.isInitialized;
    }

    public BoucleJeu getBoucle(){
        return this.boucle;
    }

    public void setBoucle(BoucleJeu nouvelleBoucle){
        this.boucle = nouvelleBoucle;
    }

}