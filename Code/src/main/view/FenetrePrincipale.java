package main.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import main.model.*;
import main.util.CustomFont;
import main.util.LookAndFeel;

/**
 * Permet de modéliser la fenêtre du programme et de la paramétrer
 */
public class FenetrePrincipale extends JFrame{
    private Jeu jeu;
    private BoucleJeu boucle;

    private Environnement currentEnvironnement;
    private Sauvegardes sauvegardes;

    private final CardLayout layout = new CardLayout();
    private boolean isInitialized = false;
    private boolean continuer = false;
    private final NouvellePartie nouvellePartie;
    private long tempsTotal;
    private GameOver gameOver;
    private boolean isDead;
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
        //Strings.language = "Fr";
        this.sauvegardes = new Sauvegardes(this);
        Accueil accueil = new Accueil(this);
        Jouer jouer = new Jouer(this);
        this.nouvellePartie = new NouvellePartie(this);
        Options options = new Options(this);
        OptionsEnJeu optionsEnJeu = new OptionsEnJeu(this);
        Regles regles = new Regles(this);
        this.gameOver = new GameOver(this);
        LaunchScreen launchScreen = new LaunchScreen();
        Difficulte difficulte = new Difficulte(this);


        this.add(accueil, "accueil");
        this.add(jouer, "jouer");
        this.add(nouvellePartie, "nouvellePartie");
        this.add(options, "options");
        this.add(optionsEnJeu, "optionsEnJeu");
        this.add(regles, "regles");
        this.add(gameOver, "gameOver");
        this.add(launchScreen, "launchScreen");
        this.add(sauvegardes, "sauvegardes");
        this.add(difficulte, "difficulte");

        tempsTotal = 0;

        pegi7Sound();
        this.setVisible(true);
    }

    /**
     * Réinitialise la vue de l'environnement au chargement d'une partie
     */
    private void resetEnvironnement() {
        this.currentEnvironnement = new Environnement(Lieu.CHAMBRE, this);
        this.add(this.currentEnvironnement, "environnement");
    }

    /**
     * Joue le son "Pegi 7"
     */
    private void pegi7Sound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Code/resources/music/pegi7.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
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
        if (this.getBoucle().getClip().isRunning()){
            Options.r1.setSelected(true);
            Options.r2.setSelected(false);
        }else{
            Options.r1.setSelected(false);
            Options.r2.setSelected(true);
        }
        this.layout.show(this.getContentPane(), "options");
    }

    /**
     * Affiche le panneau pour créer un nouvel avatar
     */
    public void actionNouvellePartie(){
        this.layout.show(this.getContentPane(), "nouvellePartie");
    }

    /**
     * Affiche le panneau pour créer un nouvel avatar
     */
    public void actionDifficulte(){
        this.layout.show(this.getContentPane(), "difficulte");
    }

    /**
     * Affiche le panneau qui répertorie les différentes sauvegardes existantes
     */
    public void actionContinuer(){
        isInitialized = false;
        this.continuer = true;

        this.layout.show(this.getContentPane(), "sauvegardes");

    }

    /**
     * Charge une sauvegarde
     * @param nom - nom de la sauvegarde
     */
    public void actionChargerPartie(String nom) {
        isInitialized = false;
        this.boucle.setSec(0);
        this.boucle.setRunning(true);
        this.boucle.start();
        SauvegardePartie partie = new SauvegardePartie(nom);
        this.isDead = partie.isDead();
        this.jeu = new Jeu();
        BoucleJeu.secSinceLastConnexion = partie.getTimeSinceLastConnexion();
        if(!isDead) {
            tempsTotal = partie.getTempsJeu() + (partie.getTimeSinceLastConnexion() / 1000);
        }
        else {
            tempsTotal = partie.getTempsJeu();
        }
        //TimerPanel.count = tempsTotal;

        this.jeu.setAvatar(partie.creerAvatar(nom));
        this.jeu.getAvatar().setPrincipale(this);
        this.jeu.setJoueur(new Joueur(partie.getNomJoueur()));


        this.resetEnvironnement();

        this.layout.show(this.getContentPane(), "environnement");

        isInitialized = true;

    }

    /**
     * Affiche la panneau précédent le panneau en cours
     * @param s - le nom du panneau en cours (le bouton retour est présent sur plusieurs panneaux)
     */
    public void actionRetour(String s){
        this.boucle.setRunning(false);

        switch (s) {
            case "GameOver":
                this.layout.show(this.getContentPane(), "accueil");
                if(this.boucle.getMusic().equals("death")) {
                    this.boucle.getClip().stop();
                    this.boucle.playGameMusic();
                }
                break;
            case "Jouer":
            case "Options":
            case "Regles":
            case "OptionsEnJeu":
                this.layout.show(this.getContentPane(), "accueil");
                break;
            case "Difficulte":
            case "Sauvegardes":
                this.layout.show(this.getContentPane(), "jouer");
                break;
            case "NouvellePartie":
                this.layout.show(this.getContentPane(), "difficulte");
                break;
        }
    }


    /**
     * Sauvegarde la partie
     */
    public void actionSauvegarde() {
        if(this.tempsTotal == 0) {
            this.tempsTotal = this.boucle.getSec();
        }
        try {
            if(!isDead) {
                new SauvegardePartie(this.jeu.getJoueur().getNom(), this.jeu.getAvatar(), (this.tempsTotal + this.boucle.getSec()), isDead);
            } else {
                new SauvegardePartie(this.jeu.getJoueur().getNom(), this.jeu.getAvatar(), this.tempsTotal, isDead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //AFFICHAGE POUR TESTS
        long minutes = (BoucleJeu.secSinceLastConnexion / 1000) / 60;
        long seconds = (BoucleJeu.secSinceLastConnexion / 1000) % 60;
        System.out.println("TEMPS DEPUIS DERNIERE CONNEXION : " + minutes + " min, " + seconds + " secs");
        //FIN AFFICHAGE
    }

    /**
     * Affiche le panneau correspondant à la chambre (Panneau de départ pour une nouvelle partie)
     */
    public void actionValider(){
        this.isDead = false;
        System.out.println("valider");
        this.tempsTotal = 0;
        this.jeu = new Jeu(NouvellePartie.nomJoueur.getText(), NouvellePartie.nomAvatar.getText(), NouvellePartie.monChoix, this);

        this.boucle.setRunning(true);
        this.boucle.start();
        this.boucle.setIsdifficultySet(false);
        this.boucle.setSec(0);

        this.resetEnvironnement();

        this.layout.show(this.getContentPane(), "environnement");
        isInitialized = true;


    }

    /**
     * Vérifie que une autre partie n'a pas déjà le même nom de joueur et le même nom d'Avatar.
     * @return false si les noms sont déjà pris
     */
    public boolean isNameValid() {
        boolean isValid = true;
        Sauvegardes save = new Sauvegardes();
        Set<String> hset = save.listFilesUsingJavaIO(".");
        for(String s : hset) {
            if (s.toLowerCase().endsWith(".json")) {
                String[] tokens = s.split("\\.");
                String nameJA = tokens[0];
                String[] decoup = nameJA.split("-");
                String nameJ = decoup[0];
                String nameA = decoup[1];
                if((NouvellePartie.nomJoueur.getText()).equals(nameJ) && (NouvellePartie.nomAvatar .getText()).equals(nameA)) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    /**
     * Change le lieu dans lequel se trouve l'avatar
     * @param lieu - le lieu actuel de l'avatar
     * @param orientation - le côté vers lequel se dirige l'avatar (gauche ou droite)
     */
    public void actionChangementEnvironnement(Lieu lieu, String orientation){

        if(orientation.equals("Gauche")){
            switch (lieu) {
                case CHAMBRE:
                    this.currentEnvironnement.changerLieu(this, Lieu.LAVER);
                    break;
                case LAVER:
                    this.currentEnvironnement.changerLieu(this, Lieu.MANGER);
                    break;
                case MANGER:
                    this.currentEnvironnement.changerLieu(this, Lieu.JOUER);
                    break;
            }
        }else if(orientation.equals("Droite")){
            switch (lieu) {
                case JOUER:
                    this.currentEnvironnement.changerLieu(this, Lieu.MANGER);
                    break;
                case LAVER:
                    this.currentEnvironnement.changerLieu(this, Lieu.CHAMBRE);
                    break;
                case MANGER:
                    this.currentEnvironnement.changerLieu(this, Lieu.LAVER);
                    break;
            }
        }
    }

    /**
     * Ouvre le menu des options depuis le jeu
     */
    public void actionOptionsEnJeu() {
        if (this.getBoucle().getClip().isRunning()){
            OptionsEnJeu.r1.setSelected(true);
            OptionsEnJeu.r2.setSelected(false);
        }else{
            OptionsEnJeu.r1.setSelected(false);
            OptionsEnJeu.r2.setSelected(true);
        }
        this.layout.show(this.getContentPane(), "optionsEnJeu");
    }

    //GETTERS

    /**
     * Retourne le jeu
     * @return jeu
     */
    public Jeu getJeu(){
        return this.jeu;
    }

    /**
     * Retourne la vue de l'environnement
     * @return vue de l'environnement
     */
    public Environnement getCurrentEnvironnement() {
        return this.currentEnvironnement;
    }

    /**
     * Vérifie si la partie est lancée
     * @return vrai si la partie est lancée
     */
    public boolean getIsInitialized() {
        return this.isInitialized;
    }

    /**
     * Retourne la boucle de jeu
     * @return boucle de jeu
     */
    public BoucleJeu getBoucle(){
        return this.boucle;
    }

    /**
     * Retourne le boolean continuer (si le joueur à cliqué sur continuer)
     * @return le boolean continuer
     */
    public boolean getContinuer() {
        return this.continuer;
    }

    /**
     * Le getter de nouvelle partie
     * @return nouvelle partie
     */
    public NouvellePartie getNouvellePartie() {
        return this.nouvellePartie;
    }

    /**
     * Retourne le layout de la fenêtre
     * @return layout de la fenêtre
     */
    public CardLayout getLayout(){return  this.layout;}

    /**
     * Retourne le temps total de jeu
     * @return temps total de jeu
     */
    public long getTempsTotal(){return this.tempsTotal;}

    /**
     * le getter de sauvegardes
     * @return sauvegardes
     */
    public Sauvegardes getSauvegardes() {
        return this.sauvegardes;
    }

    // SETTERS

    /**
     * Modifie la boucle du jeu
     * @param nouvelleBoucle - nouvelle boucle du jeu
     */
    public void setBoucle(BoucleJeu nouvelleBoucle){
        this.boucle = nouvelleBoucle;
    }

    /**
     * Le getter de gameOver
     * @return gameOver
     */
    public GameOver getGameOver() {
        return this.gameOver;
    }

    /**
     * Le setter de sauvegardes
     * @param sauvegardes une instance de Sauvegardes
     */
    public void setSauvegardes(Sauvegardes sauvegardes) {
        this.sauvegardes = sauvegardes;
    }

    /**
     * Le setter de isDead
     * @param bool un boolean
     */
    public void setIsDead(boolean bool) {
        isDead = bool;
    }

    /**
     * le getter de isDead
     * @return isDead
     */
    public boolean isDead() {
        return isDead;
    }
}
