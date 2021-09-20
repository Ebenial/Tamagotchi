package main.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.util.LookAndFeel;
import main.model.Jeu;

/**
 * Permet de modéliser la fenêtre du programme et de la paramétrer
 */
public class FenetrePrincipale extends JFrame{

    private JPanel accueil;
    private JPanel jouer;
    private JPanel nouvellePartie;
    private JPanel sauvegardes;
    private JPanel options;
    private JPanel regles;
    private JPanel jardin;
    private JPanel cuisine;
    private JPanel chambre;
    private JPanel douche;
    private Jeu jeu;
    private CardLayout layout = new CardLayout();

    /**
     * Créé la fenêtre principale du jeu
     */
    public FenetrePrincipale(){

        LookAndFeel.initLookAndFeel();

        this.setTitle("Tamagotchi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

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
        this.chambre = new Chambre(this);
        this.douche = new Douche(this);
        this.cuisine = new Cuisine(this);
        this.jardin = new Jardin(this);

        this.add(accueil, "accueil");
        this.add(jouer, "jouer");
        this.add(nouvellePartie, "nouvellePartie");
        this.add(options, "options");
        this.add(regles, "regles");
        this.add(sauvegardes, "sauvegardes");
        this.add(chambre, "chambre");
        this.add(douche, "douche");
        this.add(cuisine, "cuisine");
        this.add(jardin, "jardin");

        this.setVisible(true);
    }

    /**
     * Permet de quitter le programme après confirmation
     */
    public void actionQuitter(){
        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if(confirmation == JOptionPane.YES_OPTION){
            this.dispose();
            //System.exit(0);
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

        if(s == "Jouer"){
            this.layout.show(this.getContentPane(), "accueil");
        }else if(s == "NouvellePartie"){
            this.layout.show(this.getContentPane(), "jouer");
        }else if(s == "Options"){
            this.layout.show(this.getContentPane(), "accueil");
        }else if(s == "Regles"){
            this.layout.show(this.getContentPane(), "accueil");
        }else if(s == "Sauvegardes"){
            this.layout.show(this.getContentPane(), "jouer");
        }
    }

    /**
     * Affiche le panneau correspondant à la chambre (Panneau de départ pour une nouvelle partie)
     */
    public void actionValider(){

        this.jeu = new Jeu(NouvellePartie.nomJoueur.getText(), NouvellePartie.nomAvatar.getText(), NouvellePartie.monChoix);
        System.out.println(NouvellePartie.monChoix);
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
        if(orientation == "Gauche"){
            if(lieu == "Chambre"){
                this.layout.show(this.getContentPane(), "douche");
            }else if(lieu == "Douche"){
                this.layout.show(this.getContentPane(), "cuisine");
            }else if(lieu == "Cuisine"){
                this.layout.show(this.getContentPane(), "jardin");
            }
        }else if(orientation == "Droite"){
            if(lieu == "Chambre"){
                this.layout.show(this.getContentPane(), "cuisine");
            }else if(lieu == "Douche"){
                this.layout.show(this.getContentPane(), "chambre");
            }else if(lieu == "Cuisine"){
                this.layout.show(this.getContentPane(), "douche");
            }else if(lieu == "Jardin"){
                this.layout.show(this.getContentPane(), "cuisine");
            }
        }
    }
}