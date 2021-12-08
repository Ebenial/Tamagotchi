package main.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.util.LookAndFeel;
import main.model.BoucleJeu;
import main.model.Jeu;

/**
 * Permet de modéliser la fenêtre du programme et de la paramétrer
 */
public class FenetrePrincipale extends JFrame{

    private Accueil accueil;
    private Jouer jouer;
    private NouvellePartie nouvellePartie;
    private Sauvegardes sauvegardes;
    private Options options;
    private Regles regles;
    private Environnement jardin;
    private Environnement cuisine;
    private Environnement chambre;
    private Environnement douche;
    private Jeu jeu;
    private CardLayout layout = new CardLayout();

    /**
     * Créé la fenêtre principale du jeu
     */
    public FenetrePrincipale(){

        LookAndFeel.initLookAndFeel();

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
        new Thread(new BoucleJeu()).start();
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
        this.layout.show(this.getContentPane(), "sauvegardes");
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
     * Affiche le panneau correspondant à la chambre (Panneau de départ pour une nouvelle partie)
     */
    public void actionValider(){

        this.jeu = new Jeu(NouvellePartie.nomJoueur.getText(), NouvellePartie.nomAvatar.getText(), NouvellePartie.monChoix);

        this.chambre = new Environnement("Chambre", this);
        this.douche = new Environnement("Douche", this);
        this.cuisine = new Environnement("Cuisine", this);
        this.jardin = new Environnement("Jardin", this);

        this.add(chambre, "chambre");
        this.add(douche, "douche");
        this.add(cuisine, "cuisine");
        this.add(jardin, "jardin");

        this.layout.show(this.getContentPane(), "chambre");
    }

    /**
     * Change le lieu dans lequel se trouve l'avatar
     * @param lieu - le lieu actuel de l'avatar
     * @param orientation - le côté vers lequel se dirige l'avatar (gauche ou droite)
     */
    public void actionChangementEnvironnement(String lieu, String orientation){
        //TODO : définir l'ordre d'affichage des pièces de la maison
        //Ordre temporaire : Jardin -- Cuisine -- Douche -- Chambre
        if(orientation.equals("Gauche")){
            switch (lieu) {
                case "Chambre":
                    this.layout.removeLayoutComponent(this.douche);
                    this.douche = new Environnement("Douche", this);
                    this.add(douche, "douche");
                    this.layout.show(this.getContentPane(), "douche");
                    this.jeu.tempsEcoule();
                    break;
                case "Douche":
                    this.layout.show(this.getContentPane(), "cuisine");
                    this.jeu.tempsEcoule();
                    this.getContentPane().revalidate();
                    this.getContentPane().repaint();
                    break;
                case "Cuisine":
                    this.layout.show(this.getContentPane(), "jardin");
                    this.jeu.tempsEcoule();
                    this.getContentPane().revalidate();
                    this.getContentPane().repaint();
                    break;
            }
        }else if(orientation.equals("Droite")){
            switch (lieu) {
                case "Chambre":
                case "Jardin":
                    this.layout.show(this.getContentPane(), "cuisine");
                    this.jeu.tempsEcoule();
                    break;
                case "Douche":
                    this.layout.show(this.getContentPane(), "chambre");
                    this.jeu.tempsEcoule();
                    break;
                case "Cuisine":
                    this.layout.show(this.getContentPane(), "douche");
                    this.jeu.tempsEcoule();
                    break;
            }
        }
    }

    //GETTERS

    public Jeu getJeu(){
        return this.jeu;
    }
}