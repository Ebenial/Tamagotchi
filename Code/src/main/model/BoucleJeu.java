package main.model;

import main.util.PopUp;
import main.util.TimerPanel;
import main.view.FenetrePrincipale;
import main.view.NouvellePartie;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;
import java.time.LocalTime;

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
    private final int nbSecAutoSave = 5;
    private int nbSecEvent = 1;
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
    private int nbSecUpdateMax;
    private int nbSecUpdateMin;
    private boolean isUpdateAllInitialized = false;
    private Clip clip;
    private String music;
    private boolean isDifficultySet = false;
    private long sec;

    public synchronized void start() {
        myThread = new Thread(() -> {

            playGameMusic();

            sec = 0;
            //Temps petit pour les test, c'est ici qu'il faut changer les valeurs de temps d'update

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
                //Bouger la ligne setPrincipale
                if(principale.getIsInitialized()) {
                    if(principale.getJeu().getAvatar().getSante() <= 0 || principale.getJeu().getAvatar().getBonheur() <= 0){
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
                        if(sec % nbSecUpdateNourriture == 0) {
                            updateNourriture(nbNourriture);
                            updateStatsWithStats();
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

        running = true;
        run(); // Pourquoi appeler run ?
    }

    /**
     * Update toutes les 10 heures
     */
    private void setFacile() {
        this.nbSecUpdateMax = 36000;
        this.nbSecUpdateMin = (int) (36000 / 2);
        this.nbSecUpdateBonheur = 36000;
        this.nbSecUpdateSante = 36000;
        this.nbSecUpdateDivertissement = 36000;
        this.nbSecUpdateEnergie = 36000;
        this.nbSecUpdateHygiene = 36000;
        this.nbSecUpdateNourriture = 36000;
        //Event toutes les heures
        this.nbSecEvent = 3600;
        //Peut faire une action une fois par heure
        this.timeCanEat = 3600;
        this.timeCanPlay = 3600;
        this.timeCanShower = 3600;
        this.timeCanSleep = 3600;
    }

    /**
     * Update toutes les 6 heures
     */
    private void setNormal() {
        //toutes les 6h
        this.nbSecUpdateMax = 21600;
        this.nbSecUpdateMin = (int) (21600 / 2);
        this.nbSecUpdateBonheur = 21600;
        this.nbSecUpdateSante = 21600;
        this.nbSecUpdateDivertissement = 21600;
        this.nbSecUpdateEnergie = 21600;
        this.nbSecUpdateHygiene = 21600;
        this.nbSecUpdateNourriture = 21600;
        //Possible event toutes les 12 heures
        this.nbSecEvent = 43200;
        //Peut faire une action une fois par heure
        this.timeCanEat = 18000; //5h
        this.timeCanPlay = 10800; //3h
        this.timeCanShower = 43200; //12h
        this.timeCanSleep = 43200; //possiblement a changer 12h
    }


    private void setDifficile() {
        //Updates toute les 3h
        this.nbSecUpdateMax = 10800;
        this.nbSecUpdateMin = (int) (10800 / 2);
        this.nbSecUpdateBonheur = 10800;
        this.nbSecUpdateSante = 10800;
        this.nbSecUpdateDivertissement = 10800;
        this.nbSecUpdateEnergie = 10800;
        this.nbSecUpdateHygiene = 10800;
        this.nbSecUpdateNourriture = 10800;
        //Possible event toutes les 24 heures
        this.nbSecEvent = 86400;
        //Peut faire une action une fois par heure
        this.timeCanEat = 25200; //7h
        this.timeCanPlay = 21600; //6h
        this.timeCanShower = 43200; //12h
        this.timeCanSleep = 43200; //possiblement a changer 12h
    }

    private void setLegendaire() {
        this.nbSecUpdateMax = 7;
        this.nbSecUpdateMin = (int) (7 / 2);
        this.nbSecUpdateBonheur = 7;
        this.nbSecUpdateSante = 7;
        this.nbSecUpdateDivertissement = 7;
        this.nbSecUpdateEnergie = 7;
        this.nbSecUpdateHygiene = 7;
        this.nbSecUpdateNourriture = 7;
        this.nbSecEvent = 5;
        this.timeCanEat = 4;
        this.timeCanPlay = 4;
        this.timeCanShower = 4;
        this.timeCanSleep = 4;
    }

    private boolean isEvent() {
        boolean evt = false;
        int isEv = (int) (Math.random() * 60);
        if(isEv == 3 || isEv == 12 || isEv == 26 || isEv == 33 || isEv == 38 || isEv == 45 || isEv == 57) {
            evt = true;
        }
        return evt;
    }

    private void theEvent() {
        String[] listeEvent = {"malade", "anniversaire", "soiree", "amoureux", "sport", "jouer", "restaurant"};
        String alea = listeEvent[(int) (Math.random() * 7)];
        switch (alea) {
            case "malade":
                updateSante(-2);
                 new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est tombé malade !<br><br> Sa SANTE baisse de 2 points.")+"</html>", principale);
                 break;
            case "anniversaire":
                updateDivertissement(1);
                updateBonheur(1);
                new PopUp("<html><center><br>"+("C'est l'anniversaire de "+ principale.getJeu().getAvatar().getNom()+ " !<br><br> Son BONHEUR augmente de 1 point<br><br>Son DIVERTISSEMENT augmente de 1 point.")+"</html>", principale);
                break;
            case "soiree":
                updateDivertissement(1);
                updateBonheur(1);
                new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " fait une soirée !<br><br> Son BONHEUR augmente de 1 point<br><br>Son DIVERTISSEMENT augmente de 1 point.")+"</html>", principale);
                break;
            case "amoureux":
                updateBonheur(1);
                updateHygiene(2);
                new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est tombé amoureux !<br><br> Son BONHEUR augmente de 1 point<br><br>Son HYGIENE augmente de 1 point.")+"</html>", principale);
                break;
            case "sport":
                updateEnergie(-1);
                updateSante(1);
                new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " fait du sport !<br><br> Sa SANTE augmente de 1 point<br><br>Son ENERGIE baisse de 1 point.")+"</html>", principale);
                break;
            case "jouer":
                updateDivertissement(2);
                new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " s'amuse !<br><br> Son DIVERTISSEMENT augmente de 2 points.")+"</html>", principale);
                break;
            case "restaurant":
                updateNourriture(2);
                new PopUp("<html><center><br>"+(principale.getJeu().getAvatar().getNom()+ " est au restaurant !<br><br> Sa NOURRITURE augmente de 2 points.")+"</html>", principale);
                break;
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
        else if (avatar.getHygiene() > 3 && avatar.getHygiene() < 9 || avatar.getNourriture() > 3 && avatar.getNourriture() < 9) {
            this.nbSante = 1;
            int newUpdate = (int) (this.nbSecUpdateSante * 1.1);
            if(newUpdate <= nbSecUpdateMax) {
                this.nbSecUpdateSante = newUpdate;
            }
        }

        if(avatar.getHygiene() <= 3 && avatar.getDivertissement() <= 3) {
            int newUpdate = (int) (this.nbSecUpdateBonheur * 0.9);
            if(newUpdate >= nbSecUpdateMin) {
                this.nbSecUpdateBonheur = newUpdate;
            }
        }

        //Bonheur
        if(avatar.getEnergie() <= 3 || avatar.getDivertissement() <= 3) {
            this.nbBonheur = -1;
        }
        else if(avatar.getEnergie() > 30 && avatar.getEnergie() < 9 || avatar.getDivertissement() > 3 && avatar.getDivertissement() < 9) {
            this.nbBonheur = 1;
            int newUpdate = (int) (this.nbSecUpdateBonheur * 1.1);
            if(newUpdate <= nbSecUpdateMax) {
                this.nbSecUpdateBonheur = newUpdate;
            }
        }

    }


    private void updateStatWithStatsWhileDisconnect() {

        //Stats avant update
        int nourritureBeforeUpdate = this.principale.getJeu().getAvatar().getNourriture();
        int energieBeforeUpdate = this.principale.getJeu().getAvatar().getEnergie();
        int hygieneBeforeUpdate = this.principale.getJeu().getAvatar().getHygiene();
        int divertissementBeforeUpdate = this.principale.getJeu().getAvatar().getDivertissement();

        //Nombre d'update pendant la deconnexion
        int nbUpdateNourriture = (int) numberOfUpdate(nbSecUpdateNourriture);
        int nbUpdateEnergie = (int) numberOfUpdate(nbSecUpdateEnergie);
        int nbUpdateHygiene = (int) numberOfUpdate(nbSecUpdateHygiene);
        int nbUpdateDivertissement = (int) numberOfUpdate(nbSecUpdateDivertissement);
        int eventInFourthUpdate = (int) (numberOfUpdate(nbSecEvent) / 4);


        int usefullNbUpdateNourriture = nbUpdateNourriture;
        int usefullNbUpdateEnergie = nbUpdateEnergie;
        int usefullNbUpdateHygiene = nbUpdateHygiene;
        int usefullNbUpdateDivertissement = nbUpdateDivertissement;

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

        //Remise a niveau des valeurs a utiliser
        usefullNbUpdateNourriture = nbUpdateNourriture;
        usefullNbUpdateEnergie = nbUpdateEnergie;
        usefullNbUpdateHygiene = nbUpdateHygiene;
        usefullNbUpdateDivertissement = nbUpdateDivertissement;

        //Fait baisser le nombre d'updateNourriture jusqu'a ce que NourritureBeforeUpdate = 3.
        while (nourritureBeforeUpdate >= 3 && usefullNbUpdateNourriture > 0) {

            if(nourritureBeforeUpdate < 9) {
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
            if(energieBeforeUpdate < 9) {
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
            if(hygieneBeforeUpdate < 9) {
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
            if(divertissementBeforeUpdate < 9) {
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

    public void setIsdifficultySet(boolean bool) {
        this.isDifficultySet = bool;
    }

    public long getSec() {
        return this.sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

}


