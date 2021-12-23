package main.model;

import main.view.FenetrePrincipale;

import javax.swing.*;

public class BoucleJeu implements Runnable{


    public static Thread myThread;
    public boolean running = true;
    public static long secSinceLastConnexion;
    private final FenetrePrincipale principale;
    private int nbSecUpdateSante = 20;
    private int nbSecUpdateBonheur = 20;
    private int nbSecUpdateNourriture = 20;
    private int nbSecUpdateEnergie = 20;
    private int nbSecUpdateHygiene = 20;
    private int nbSecUpdateDivertissement = 20;
    private int nbSecAutoSave = 5;
    private boolean isUpdateAllInitialized = false;


    public synchronized void start() {
        myThread = new Thread(){
            public void run(){
                long sec = 0;
                //Temps petit pour les test, c'est ici qu'il faut changer les valeurs de temps d'update



                while (running) {
                    System.out.println();  // ATTENTION CASSE TOUT SI ENLEVER WTF LES AMIS
                    if(principale.getIsInitialized()) {
                        if(!isUpdateAllInitialized && principale.getContinuer()) {
                            updateAll();
                            isUpdateAllInitialized = true;
                        }
                        try {
                            myThread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        //Update;
                        if(sec % nbSecUpdateSante == 0) {
                            updateSante(-1);
                        }
                        if(sec % nbSecUpdateBonheur == 0) {
                            updateBonheur(-1);
                        }
                        if(sec % nbSecUpdateNourriture == 0) {
                            updateNourriture(-1);
                        }
                        if(sec % nbSecUpdateEnergie == 0) {
                            updateEnergie(-1);
                        }
                        if(sec % nbSecUpdateHygiene == 0) {
                            updateHygiene(-1);
                        }
                        if(sec % nbSecUpdateDivertissement == 0) {
                            updateDivertissement(-1);
                        }
                        if(sec % nbSecAutoSave == 0) {
                            principale.actionSauvegarde();
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

    /**
     * Compte le nombre d'update qui aurait du se passer pour un evenement donne pendant la deconnexion du joueur
     * @param secUpdate
     * @return
     */
    private long numberOfUpdate(long secUpdate) {
        long seconds = (BoucleJeu.secSinceLastConnexion / 1000) % 60;
        return seconds / secUpdate;
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
        JLabel label1 =  principale.getCurrentEnvironnement().getSante();   //Récupération de l'affichage de la barre de santé
        label1.setIcon(principale.getJeu().choixBarreStats(sante + modif)); //Mise à jour de l'affichage de la santé avec la nouvelle valeur
    }

    /**
     * 
     * @param modif
     */
    private void updateBonheur(int modif) {

        int bonheur = this.principale.getJeu().getAvatar().getBonheur();
        this.principale.getJeu().getAvatar().setBonheur(bonheur + modif);
        //System.out.println("bonheur : " + bonheur);
        JLabel label2 =  principale.getCurrentEnvironnement().getBonheur();
        label2.setIcon(principale.getJeu().choixBarreStats(bonheur + modif));
    }

    /**
     * 
     * @param modif
     */
    private void updateNourriture(int modif) {

        int nourriture = this.principale.getJeu().getAvatar().getNourriture();
        this.principale.getJeu().getAvatar().setNourriture(nourriture + modif);
        //System.out.println("nourriture : " + nourriture);
        JLabel label1 =  principale.getCurrentEnvironnement().getNourriture();
        label1.setIcon(principale.getJeu().choixBarreStats(nourriture + modif));
    }

    /**
     * 
     * @param modif
     */
    private void updateEnergie(int modif) {

        int energie = this.principale.getJeu().getAvatar().getEnergie();
        this.principale.getJeu().getAvatar().setEnergie(energie + modif);
        //System.out.println("energie : " + energie);
        JLabel label1 =  principale.getCurrentEnvironnement().getEnergie();
        label1.setIcon(principale.getJeu().choixBarreStats(energie + modif));
    }

    /**
     * 
     * @param modif
     */
    private void updateHygiene(int modif) {

        int hygiene = this.principale.getJeu().getAvatar().getHygiene();
        this.principale.getJeu().getAvatar().setHygiene(hygiene + modif);
        //System.out.println("hygiene : " + hygiene);
        JLabel label1 =  principale.getCurrentEnvironnement().getHygiene();
        label1.setIcon(principale.getJeu().choixBarreStats(hygiene + modif));
    }

    /**
     * 
     * @param modif
     */
    private void updateDivertissement(int modif) {

        int divertissement = this.principale.getJeu().getAvatar().getDivertissement();
        this.principale.getJeu().getAvatar().setDivertissement(divertissement + modif);
        //System.out.println("divertissement : " + divertissement);
        JLabel label3 =  principale.getCurrentEnvironnement().getDivertissement();
        label3.setIcon(principale.getJeu().choixBarreStats(divertissement + modif));
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


