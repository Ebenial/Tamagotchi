package main.model;

import main.view.FenetrePrincipale;
import main.view.GameOver;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class BoucleJeu implements Runnable{


    public static Thread myThread;
    public boolean running = true;
    public static long secSinceLastConnexion;
    private final FenetrePrincipale principale;
    private int nbSecUpdateSante = 5;
    private int nbSecUpdateBonheur = 5;
    private int nbSecUpdateNourriture = 5;
    private int nbSecUpdateEnergie = 5;
    private int nbSecUpdateHygiene = 5;
    private int nbSecUpdateDivertissement = 5;
    private int nbSecAutoSave = 5;
    private long timeCanEat = 2;
    private long timeCanSleep = 2;
    private long timeCanShower = 2;
    private long timeCanPlay = 2;
    private int nbSante = 0;
    private int nbBonheur = 0;
    private int nbNourriture = -1;
    private int nbEnergie = -1;
    private int nbHygiene = -1;
    private int nbDivertissement = -1;
    private boolean isUpdateAllInitialized = false;


    public synchronized void start() {
        myThread = new Thread(){
            public void run(){
                playSound();

                long sec = 0;
                //Temps petit pour les test, c'est ici qu'il faut changer les valeurs de temps d'update



                while (running) {
                    System.out.println();  // ATTENTION CASSE TOUT SI ENLEVER WTF LES AMIS
                    //Bouger la ligne setPrincipale
                    if(principale.getIsInitialized()) {
                        if(principale.getJeu().getAvatar().getSante() <= 0 || principale.getJeu().getAvatar().getBonheur() <= 0){
                            principale.getLayout().show(principale.getContentPane(), "gameOver");
                        }
                        principale.getJeu().getAvatar().setPrincipale(principale);
                        if(!isUpdateAllInitialized && principale.getContinuer()) {
                            updateAll();
                            isUpdateAllInitialized = true;
                        }
                        try {
                            myThread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        updateStatsWithStats();

                        //Update;
                        if(sec % nbSecUpdateSante == 0) {
                            updateSante(nbSante);
                        }
                        if(sec % nbSecUpdateBonheur == 0) {
                            updateBonheur(nbBonheur);
                        }
                        if(sec % nbSecUpdateNourriture == 0) {
                            updateNourriture(nbNourriture);
                        }
                        if(sec % nbSecUpdateEnergie == 0) {
                            updateEnergie(nbEnergie);
                        }
                        if(sec % nbSecUpdateHygiene == 0) {
                            updateHygiene(nbHygiene);
                        }
                        if(sec % nbSecUpdateDivertissement == 0) {
                            updateDivertissement(nbDivertissement);
                        }
                        if(sec % nbSecAutoSave == 0) {
                            principale.actionSauvegarde();
                        }
                        if(sec % timeCanEat == 0) {
                            principale.getJeu().getAvatar().setCanEat(true);
                        }
                        if(sec % timeCanPlay == 0) {
                            principale.getJeu().getAvatar().setCanPlay(true);
                        }
                        if(sec % timeCanShower == 0) {
                            principale.getJeu().getAvatar().setCanShower(true);
                        }
                        if(sec % timeCanSleep == 0) {
                            principale.getJeu().getAvatar().setCanSleep(true);
                        }

                        sec++;
                    }
                }

            }
        };
        myThread.start();
        running = true;
        run();
    }

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Code/resources/music/Tamagotchi.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Compte le nombre d'update qui aurait du se passer pour un evenement donne pendant la deconnexion du joueur
     * @param secUpdate
     * @return
     */
    private long numberOfUpdate(long secUpdate) {
        long seconds = (BoucleJeu.secSinceLastConnexion / 1000) % 60;
        return seconds / secUpdate;
    }

    private void updateStatsWithStats() {
        Avatar avatar = principale.getJeu().getAvatar();

        //Sante
        if(avatar.getHygiene() <= 3 || avatar.getNourriture() <= 3) {
            this.nbSante = -1;
        }
        else if (avatar.getHygiene() > 3 && avatar.getHygiene() < 9 || avatar.getDivertissement() > 3 && avatar.getDivertissement() < 90) {
            this.nbSante = 1;
        }

        //Bonheur
        if(avatar.getEnergie() <= 3 || avatar.getDivertissement() <= 3) {
            this.nbBonheur = -1;
        }
        else if(avatar.getEnergie() > 30 && avatar.getEnergie() < 9 || avatar.getDivertissement() > 3 && avatar.getDivertissement() < 9) {
            this.nbBonheur = 1;
        }

    }

    /**
     * Update les stats du joueur après une reconnexion
     */
    private void updateAll() {
        System.out.println("UPDATE ALL");
        System.out.println("nbUpdate : " + (int) numberOfUpdate(nbSecUpdateSante));
        updateSante((int) numberOfUpdate(-nbSecUpdateSante));
        updateBonheur((int) numberOfUpdate(-nbSecUpdateBonheur));
        updateNourriture((int) numberOfUpdate(-nbSecUpdateNourriture));
        updateEnergie((int) numberOfUpdate(-nbSecUpdateEnergie));
        updateHygiene((int) numberOfUpdate(-nbSecUpdateHygiene));
        updateDivertissement((int) numberOfUpdate(-nbSecUpdateDivertissement));
    }

    public synchronized void stop() {
        running = false;
    }

    /**
     * Modifie la santé de l'avatar de la valeur passé en paramètre
     * @param modif - la valeur a jouter ou soustraire à la santé actuelle de l'avatar
     */
    private void updateSante(int modif) {

        int sante = this.principale.getJeu().getAvatar().getSante();        //Récupération de la santé actuelle
        this.principale.getJeu().getAvatar().setSante(sante + modif);       //Mise à jour de la valeur de santé de l'avatar
        //System.out.println("sante : " + sante);
    }

    /**
     * 
     * @param modif
     */
    private void updateBonheur(int modif) {

        int bonheur = this.principale.getJeu().getAvatar().getBonheur();
        this.principale.getJeu().getAvatar().setBonheur(bonheur + modif);
    }

    /**
     * 
     * @param modif
     */
    private void updateNourriture(int modif) {

        int nourriture = this.principale.getJeu().getAvatar().getNourriture();
        this.principale.getJeu().getAvatar().setNourriture(nourriture + modif);

    }

    /**
     * 
     * @param modif
     */
    private void updateEnergie(int modif) {

        int energie = this.principale.getJeu().getAvatar().getEnergie();
        this.principale.getJeu().getAvatar().setEnergie(energie + modif);

    }

    /**
     * 
     * @param modif
     */
    private void updateHygiene(int modif) {

        int hygiene = this.principale.getJeu().getAvatar().getHygiene();
        this.principale.getJeu().getAvatar().setHygiene(hygiene + modif);

    }

    /**
     * 
     * @param modif
     */
    private void updateDivertissement(int modif) {

        int divertissement = this.principale.getJeu().getAvatar().getDivertissement();
        this.principale.getJeu().getAvatar().setDivertissement(divertissement + modif);

    }

    /**
     * 
     */
    public void updateAllStats(){
        updateBonheur(0);
        updateSante(0);
        updateDivertissement(0);
        updateEnergie(0);
        updateHygiene(0);
        updateNourriture(0);
    }


    @Override
    public void run() {

    }

    public BoucleJeu(FenetrePrincipale principale) {
        this.principale = principale;
        this.principale.setBoucle(this);
        start();
    }

}


