package main.model;

import main.util.PopUp;
import main.util.TimerPanel;
import main.view.FenetrePrincipale;
import main.view.GameOver;
import main.view.NouvellePartie;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.io.File;

public class BoucleJeu implements Runnable{
    public static Thread myThread;
    public boolean running = true;
    public static long secSinceLastConnexion;
    private FenetrePrincipale principale;
    private int nbSecUpdateSecondaryStats;
    private int nbSecUpdateSante = 5;
    private int nbSecUpdateBonheur = 5;
    private final int nbSecAutoSave = 5;
    private int nbSecEvent = 1;
    private long timeForAction = 2;

    private int nbSante = 0;
    private int nbBonheur = 0;
    private int nbSecUpdateMax;
    private int nbSecUpdateMin;
    private boolean isUpdateAllInitialized = false;
    private Clip clip;
    private String music;
    private boolean isDifficultySet = false;
    private long sec;
    private boolean isDisplayEvent = true;

    public synchronized void start() {
        myThread = new Thread(() -> {
            sec = 0;

            while (running) {
                System.out.println(); // ATTENTION: parfois ne fonctionne pas sans ce print
                if(!isDifficultySet && NouvellePartie.difficulty != null) {
                    switch (NouvellePartie.difficulty) {
                        case "facile":
                            setFacile();
                            isDifficultySet = true;
                            break;
                        case "normal":
                            setNormal();
                            isDifficultySet = true;
                            break;
                        case "difficile":
                            setDifficile();
                            isDifficultySet = true;
                            break;
                        case "legendaire":
                            setLegendaire();
                            isDifficultySet = true;
                            break;
                        default:
                            break;
                    }
                }
                if(principale.getIsInitialized()) {
                    if(principale.getJeu().getAvatar().getSante() <= 0 || principale.getJeu().getAvatar().getBonheur() <= 0 || principale.isDead()){
                        JLabel result = principale.getGameOver().getResultat();
                        long count = principale.getCurrentEnvironnement().getTimerPanel().getCount();

                        int sec = (int) count % 60;
                        int min = (int) (count / 60) % 60;
                        int hours = (int) ((count / 60) / 60)%24;
                        int days = (int) (((count / 60) / 60) / 24);

                        String strSec = (sec < 10) ? "0" + sec : Integer.toString(sec);
                        String strmin = (min < 10) ? "0" + min : Integer.toString(min);
                        String strHours = (hours < 10) ? "0" + hours : Integer.toString(hours);
                        String strDays = (days < 10) ? "0" + days : Integer.toString(days);

                        if(!principale.isDead()) {
                            result.setText(strDays + "j | " + strHours + "h | " + strmin + "m | " + strSec+"s");
                        } else {
                            result.setText("Votre Keneil se trouve actuellement au paradis des Keneils !");
                        }


                        principale.setIsDead(true);
                        principale.actionSauvegarde();
                        principale.getLayout().show(principale.getContentPane(), "gameOver");
                        running = false;
                        clip.stop();
                        playDeathMusic();
                        music = "death";

                    }
                    principale.getJeu().getAvatar().setPrincipale(principale);
                    if(!isUpdateAllInitialized && principale.getContinuer()) {
                        isUpdateAllInitialized = true;
                        updateStatWithStatsWhileDisconnect();
                    }
                    try {
                        myThread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(sec > 0) {
                        //Update;
                        if(sec % nbSecUpdateSante == 0) {
                            updateSante(nbSante);
                        }
                        if(sec % nbSecUpdateBonheur == 0) {
                            updateBonheur(nbBonheur);
                        }
                        if(sec % nbSecUpdateSecondaryStats == 0) {
                            updateNourriture(-1);
                            updateEnergie(-1);
                            updateHygiene(-1);
                            updateDivertissement(-1);
                            updateStatsWithStats();
                        }
                        if(sec % nbSecAutoSave == 0) {
                            principale.actionSauvegarde();
                            principale.getSauvegardes().updateSaves();
                            principale.getSauvegardes().writeBestScore();
                        }
                        if(sec % timeForAction == 0) {
                            principale.getJeu().getAvatar().setCanEat(true);
                            principale.getJeu().getAvatar().setCanPlay(true);
                            principale.getJeu().getAvatar().setCanShower(true);
                            principale.getJeu().getAvatar().setCanSleep(true);
                            principale.getCurrentEnvironnement().actionState(true);
                        }

                        if(sec % nbSecEvent == 0) {
                            if(isEvent()) {
                                theEvent();
                            }
                        }
                    }
                    sec++;
                }
            }

        });
        myThread.start();

        principale.getLayout().show(principale.getContentPane(), "launchScreen");
        try {
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        principale.getLayout().show(principale.getContentPane(), "accueil");

    }

    /**
     * Update toutes les 10 heures
     */
    private void setFacile() {
        this.nbSecUpdateMax = 36000;
        this.nbSecUpdateMin = (int) (nbSecUpdateMax / 2);
        this.nbSecUpdateBonheur = nbSecUpdateMax;
        this.nbSecUpdateSante = nbSecUpdateMax;
        this.nbSecUpdateSecondaryStats = nbSecUpdateMax;
        //Event toutes les heures
        this.nbSecEvent = 3600;
        //Peut faire une action une fois par heure
        this.timeForAction = 3600;

    }

    /**
     * Update toutes les 6 heures
     */
    private void setNormal() {
        //toutes les 6h
        this.nbSecUpdateMax = 21600;
        this.nbSecUpdateMin = (int) (nbSecUpdateMax / 2);
        this.nbSecUpdateBonheur = nbSecUpdateMax;
        this.nbSecUpdateSante = nbSecUpdateMax;
        this.nbSecUpdateSecondaryStats = nbSecUpdateMax;
        //Possible event toutes les 5 heures
        this.nbSecEvent = 18000;
        //Peut faire une action une fois toutes les 3h
        this.timeForAction = 10800;

    }


    private void setDifficile() {
        //Updates toute les 3h
        this.nbSecUpdateMax = 10800;
        this.nbSecUpdateMin = (int) (nbSecUpdateMax / 2);
        this.nbSecUpdateBonheur = nbSecUpdateMax;
        this.nbSecUpdateSante = nbSecUpdateMax;
        this.nbSecUpdateSecondaryStats = nbSecUpdateMax;
        //Possible event toutes les 10 heures
        this.nbSecEvent = 36000;
        //Peut faire une action toutes les 5 heures
        this.timeForAction = 18000;

    }

    private void setLegendaire() {
        this.nbSecUpdateMax = 7;
        this.nbSecUpdateMin = (int) (nbSecUpdateMax / 2);
        this.nbSecUpdateBonheur = nbSecUpdateMax;
        this.nbSecUpdateSante = nbSecUpdateMax;
        this.nbSecUpdateSecondaryStats = nbSecUpdateMax;
        this.nbSecEvent = 10;
        this.timeForAction = 4;

    }

    private boolean isEvent() {
        boolean evt = false;
        int isEv = (int) (Math.random() * 90);
        if(isEv == 3 || isEv == 12 || isEv == 26 || isEv == 38 || isEv == 45 || isEv == 57 || isEv == 64 || isEv == 75 || isEv == 87) {
            evt = true;
        }
        return evt;
    }

    private void theEvent() {
        String[] listeEvent = {"malade", "anniversaire", "soiree", "amoureux", "sport", "jouer", "restaurant", "vacances", "depression"};
        String alea = listeEvent[(int) (Math.random() * 9)];
        System.out.println("ALEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA : " + alea);
        switch (alea) {
            case "malade":
                updateSante(-2);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est tombé malade !<br><br> Sa SANTE baisse de 2 points.")+"</html>", principale);
                }
                break;
            case "anniversaire":
                updateDivertissement(1);
                updateBonheur(1);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+("C'est l'anniversaire de "+ principale.getJeu().getAvatar().getNom()+ " !<br><br> Son BONHEUR augmente de 1 point<br><br>Son DIVERTISSEMENT augmente de 1 point.")+"</html>", principale);
                }
                break;
            case "soiree":
                updateDivertissement(1);
                updateBonheur(1);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " fait une soirée !<br><br> Son BONHEUR augmente de 1 point<br><br>Son DIVERTISSEMENT augmente de 1 point.")+"</html>", principale);
                }
                break;
            case "amoureux":
                updateBonheur(1);
                updateHygiene(2);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est tombé amoureux !<br><br> Son BONHEUR augmente de 1 point<br><br>Son HYGIENE augmente de 1 point.")+"</html>", principale);
                }
                break;
            case "sport":
                updateEnergie(-1);
                updateSante(1);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " fait du sport !<br><br> Sa SANTE augmente de 1 point<br><br>Son ENERGIE baisse de 1 point.")+"</html>", principale);
                }
                break;
            case "jouer":
                updateDivertissement(2);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " s'amuse !<br><br> Son DIVERTISSEMENT augmente de 2 points.")+"</html>", principale);
                }
                break;
            case "restaurant":
                updateNourriture(2);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est au restaurant !<br><br> Sa NOURRITURE augmente de 2 points.")+"</html>", principale);
                }
                break;
            case "vacances":
                updateEnergie(3);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est en vacances !<br><br> Son ENERGIE augmente de 3 points.")+"</html>", principale);
                }
            case "depression":
                updateBonheur(-2);
                if(isDisplayEvent) {
                    new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est en dépression !<br><br> Son BONHEUR baisse de 2 points.")+"</html>", principale);
                }
        }
    }

    public Clip getClip() {
        return this.clip;
    }

    public String getMusic() {
        return this.music;
    }

    public void playGameMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Code/resources/music/Tamagotchi.wav").getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playDeathMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Code/resources/music/death.wav").getAbsoluteFile());
            this.clip = AudioSystem.getClip();
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

        return (secSinceLastConnexion / 1000) / secUpdate;
    }

    private void updateStatsWithStats() {
        Avatar avatar = principale.getJeu().getAvatar();

        if(avatar.getHygiene() <= 3 && avatar.getNourriture() <= 3) {
            int newUpdate = (int) (this.nbSecUpdateSante * 0.9);
            if(newUpdate >= nbSecUpdateMin) {
                this.nbSecUpdateSante = newUpdate;
            }
        }
        //Sante
        if(avatar.getHygiene() <= 3 || avatar.getNourriture() <= 3) {
            this.nbSante = -1;
        }
        else if (avatar.getHygiene() > 3 && avatar.getHygiene() <= 9 || avatar.getNourriture() > 3 && avatar.getNourriture() <= 9) {
            this.nbSante = 1;
            int newUpdate = (int) (this.nbSecUpdateSante * 1.1);
            if(newUpdate <= nbSecUpdateMax) {
                this.nbSecUpdateSante = newUpdate;
            }
        }

        if(avatar.getEnergie() <= 3 && avatar.getDivertissement() <= 3) {
            int newUpdate = (int) (this.nbSecUpdateBonheur * 0.9);
            if(newUpdate >= nbSecUpdateMin) {
                this.nbSecUpdateBonheur = newUpdate;
            }
        }

        //Bonheur
        if(avatar.getEnergie() <= 3 || avatar.getDivertissement() <= 3) {
            this.nbBonheur = -1;
        }
        else if(avatar.getEnergie() > 3 && avatar.getEnergie() <= 9 || avatar.getDivertissement() > 3 && avatar.getDivertissement() <= 9) {
            this.nbBonheur = 1;
            int newUpdate = (int) (this.nbSecUpdateBonheur * 1.1);
            if(newUpdate <= nbSecUpdateMax) {
                this.nbSecUpdateBonheur = newUpdate;
            }
        }

    }


    private void updateStatWithStatsWhileDisconnect() {
        isDisplayEvent = false;
        //Stats avant update
        int nourritureBeforeUpdate = this.principale.getJeu().getAvatar().getNourriture();
        int energieBeforeUpdate = this.principale.getJeu().getAvatar().getEnergie();
        int hygieneBeforeUpdate = this.principale.getJeu().getAvatar().getHygiene();
        int divertissementBeforeUpdate = this.principale.getJeu().getAvatar().getDivertissement();

        //Nombre d'update pendant la deconnexion
        int nbUpdateSecondaryStats = (int) numberOfUpdate(this.nbSecUpdateSecondaryStats);
        int eventInFourthUpdate = (int) (numberOfUpdate(nbSecEvent) / 4);


        int usefullNbUpdateNourriture = nbUpdateSecondaryStats;
        int usefullNbUpdateEnergie = nbUpdateSecondaryStats;
        int usefullNbUpdateHygiene = nbUpdateSecondaryStats;
        int usefullNbUpdateDivertissement = nbUpdateSecondaryStats;

        //Update les stats (sauf Sante et Bonheur) en fonction du nombre d'update qu'il y a eu lieu pendant la deconnexion + mets 1/4 des events pour une meilleur repartition
        int event = eventInFourthUpdate;
        while (usefullNbUpdateNourriture > 0) {
            if(event > 0) {
                if(isEvent()) {
                    theEvent();
                }
                event--;
            }
            updateNourriture(-1);
            usefullNbUpdateNourriture--;
        }

        event = eventInFourthUpdate;
        while (usefullNbUpdateEnergie > 0) {

            if(event > 0) {
                if(isEvent()) {
                    theEvent();
                }
                event--;
            }
            updateEnergie(-1);
            usefullNbUpdateEnergie--;
        }

        event = eventInFourthUpdate;
        while (usefullNbUpdateHygiene > 0) {

            if(event > 0) {
                if(isEvent()) {
                    theEvent();
                }
                event--;
            }
            updateHygiene(-1);
            usefullNbUpdateHygiene--;
        }

        event = eventInFourthUpdate;
        while (usefullNbUpdateDivertissement > 0) {

            if(event > 0) {
                if(isEvent()) {
                    theEvent();
                }
                event--;
            }
            updateDivertissement(-1);
            usefullNbUpdateDivertissement--;
        }
        isDisplayEvent = true;

        //Remise a niveau des valeurs a utiliser
        usefullNbUpdateNourriture = nbUpdateSecondaryStats;
        usefullNbUpdateEnergie = nbUpdateSecondaryStats;
        usefullNbUpdateHygiene = nbUpdateSecondaryStats;
        usefullNbUpdateDivertissement = nbUpdateSecondaryStats;

        //Fait baisser le nombre d'updateNourriture jusqu'a ce que NourritureBeforeUpdate = 3.
        while (nourritureBeforeUpdate >= 3 && usefullNbUpdateNourriture > 0) {

            if(nourritureBeforeUpdate <= 9) {
                int newUpdate = (int) (this.nbSecUpdateSante * 1.1);
                if(newUpdate <= nbSecUpdateMax) {
                    this.nbSecUpdateSante = newUpdate;
                }
            }
            nourritureBeforeUpdate--;
            usefullNbUpdateNourriture--;
        }

        //Fait baisser le nombre d'updateEnergoe jusqu'a ce que EnergieBeforeUpdate = 3.
        while (energieBeforeUpdate >= 3 && usefullNbUpdateEnergie> 0) {
            if(energieBeforeUpdate <= 9) {
                int newUpdate = (int) (this.nbSecUpdateBonheur * 1.1);
                if(newUpdate <= nbSecUpdateMax) {
                    this.nbSecUpdateBonheur = newUpdate;
                }
            }
            energieBeforeUpdate--;
            usefullNbUpdateEnergie--;
        }

        //Fait baisser le nombre d'updateHygiene jusqu'a ce que HygieneBeforeUpdate = 3.
        while (hygieneBeforeUpdate >=3 && usefullNbUpdateHygiene > 0) {
            if(hygieneBeforeUpdate <= 9) {
                int newUpdate = (int) (this.nbSecUpdateSante * 1.1);
                if(newUpdate <= nbSecUpdateMax) {
                    this.nbSecUpdateSante = newUpdate;
                }
            }
            hygieneBeforeUpdate--;
            usefullNbUpdateHygiene--;
        }

        //Fait baisser le nombre d'updateDivertissement jusqu'a ce que DivertissementBeforeUpdate = 3.
        while (divertissementBeforeUpdate >= 3 && usefullNbUpdateDivertissement > 0) {
            if(divertissementBeforeUpdate <= 9) {
                int newUpdate = (int) (this.nbSecUpdateBonheur * 1.1);
                if(newUpdate <= nbSecUpdateMax) {
                    this.nbSecUpdateBonheur = newUpdate;
                }
            }
            divertissementBeforeUpdate--;
            usefullNbUpdateDivertissement--;
        }

        int tmpEnergie = usefullNbUpdateEnergie;
        if(usefullNbUpdateDivertissement > usefullNbUpdateEnergie) {
            tmpEnergie = usefullNbUpdateDivertissement;
            usefullNbUpdateDivertissement = usefullNbUpdateEnergie;
        }

        int tmpNourriture = usefullNbUpdateNourriture;
        if(usefullNbUpdateHygiene > usefullNbUpdateNourriture) {
            tmpNourriture = usefullNbUpdateHygiene;
            usefullNbUpdateHygiene = usefullNbUpdateNourriture;
        }

        while (usefullNbUpdateDivertissement > 0) {
            int newUpdate = (int) (this.nbSecUpdateBonheur * 0.9);
            if(newUpdate >= nbSecUpdateMin) {
                this.nbSecUpdateBonheur = newUpdate;
            }
            usefullNbUpdateDivertissement--;
        }

        while (usefullNbUpdateHygiene > 0) {
            int newUpdate = (int) (this.nbSecUpdateSante * 0.9);
            if(newUpdate >= nbSecUpdateMin) {
                this.nbSecUpdateSante = newUpdate;
            }
            usefullNbUpdateHygiene--;
        }


        updateSante(-tmpNourriture);
        updateBonheur(-tmpEnergie);

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


    @Override
    public void run() {

    }

    public BoucleJeu(FenetrePrincipale principale) {
        this.principale = principale;
        playGameMusic();
        this.principale.setBoucle(this);
        start();
    }

    public void setIsdifficultySet(boolean bool) {
        this.isDifficultySet = bool;
    }

    public long getSec() {
        return this.sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

    public void setRunning(boolean bool) {
        this.running = bool;
    }


}


