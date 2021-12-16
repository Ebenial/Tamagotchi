package main.model;

import main.view.FenetrePrincipale;

import javax.swing.*;

public class BoucleJeu implements Runnable{


    public static Thread myThread;
    public boolean running = true;
    private final FenetrePrincipale principale;
    private int cptSant = 0;
    private int cptBonh = 0;
    private int cptNour = 0;
    private int cptEner = 0;
    private int cptHygi = 0;
    private  int cptDive = 0;


    public synchronized void start() {
        myThread = new Thread(){
            public void run(){
                long sec = 0;
                //Temps petit pour les test, c'est ici qu'il faut changer les valeurs de temps d'update
                int nbSecUpdateSante = 20;
                int nbSecUpdateBonheur = 20;
                int nbSecUpdateNourriture = 10;
                int nbSecUpdateEnergie = 5;
                int nbSecUpdateHygiene = 15;
                int nbSecUpdateDivertissement = 7;


                while (running) {
                    System.out.println(principale.getIsInitialized());  // ATTENTION CASSE TOUT SI ENLEVER WTF LES AMIS
                    if(principale.getIsInitialized()) {
                        try {
                            myThread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        //Update;
                        if(sec % nbSecUpdateSante == 0) {
                            updateSante();
                        }
                        if(sec % nbSecUpdateBonheur == 0) {
                            updateBonheur();
                        }
                        if(sec % nbSecUpdateNourriture == 0) {
                            updateNourriture();
                        }
                        if(sec % nbSecUpdateEnergie == 0) {
                            updateEnergie();
                        }
                        if(sec % nbSecUpdateHygiene == 0) {
                            updateHygiene();
                        }
                        if(sec % nbSecUpdateDivertissement == 0) {
                            updateDivertissement();
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

    public synchronized void stop() {
        running = false;
    }

    private void updateSante() {
        JLabel label1 =  principale.getCurrentEnvironnement().getSante();
        label1.setIcon(principale.getJeu().choixBarreStats(cptSant));
        cptSant++;
    }

    private void updateBonheur() {
        JLabel label2 =  principale.getCurrentEnvironnement().getBonheur();
        label2.setIcon(principale.getJeu().choixBarreStats(cptBonh));
        cptBonh++;
    }

    private void updateNourriture() {
        JLabel label1 =  principale.getCurrentEnvironnement().getNourriture();
        label1.setIcon(principale.getJeu().choixBarreStats(cptNour));
        cptNour++;
    }

    private void updateEnergie() {
        JLabel label1 =  principale.getCurrentEnvironnement().getEnergie();
        label1.setIcon(principale.getJeu().choixBarreStats(cptEner));
        cptEner++;
    }

    private void updateHygiene() {
        JLabel label1 =  principale.getCurrentEnvironnement().getHygiene();
        label1.setIcon(principale.getJeu().choixBarreStats(cptHygi));
        cptHygi++;
    }

    private void updateDivertissement() {
        JLabel label3 =  principale.getCurrentEnvironnement().getDivertissement();
        label3.setIcon(principale.getJeu().choixBarreStats(cptDive));
        cptDive++;
    }


    @Override
    public void run() {

    }

    public BoucleJeu(FenetrePrincipale principale) {
        this.principale = principale;
        start();
    }

}


