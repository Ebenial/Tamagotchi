package main.model;

import java.awt.*;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.ImageIcon;

import main.view.FenetrePrincipale;

/**
 * Classe principale qui dirige la partie
 * S'occupe des statistiques de l'avatar, des évènements ainsi que des conditions de fin de partie
 */
public class Jeu{

    FenetrePrincipale principale;
    private Joueur joueur;
    private Avatar avatar;
    private Date compteurTemps;
    private boolean enCours;

    private static LocalTime SUNRISE_TIME = LocalTime.of(6, 0);
    private static LocalTime SUNSET_TIME = LocalTime.of(20, 0);

    /**
     * Constructeur du type de jeu
     * @param nomJoueur - le pseudo du joueur humain
     * @param nomAvatar - le nom que le joueur a donné à son avatar
     * @param typeAvatar - le type d'avatar choisi (chat, chien, ...)
     */
    public Jeu(String nomJoueur, String nomAvatar, String typeAvatar, FenetrePrincipale principale){
        this.joueur = new Joueur(nomJoueur);
        this.avatar = new Avatar(typeAvatar, nomAvatar);
        this.avatar.setPrincipale(principale);
    }

    public Jeu(){}

    /**
     * Récupère la valeur de statistique de l'avatar et indique quelle image de barre de statistiques choisir en conséquence
     * @param valeur - la valeur de la statistique
     * @return l'image qui correspond à la valeur
     */
    public ImageIcon choixBarreStats(int valeur){

        ImageIcon res;

        switch(valeur){
            case 10:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques10.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 1:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques01.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 2:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques02.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 3:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques03.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 4:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques04.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 5:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques05.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 6:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques06.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 7:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques07.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 8:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques08.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 9:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques09.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            default:
                res = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Code/resources/others/barreStatistiques00.png").getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;

        }

        return res;
    }

    public boolean isDay() {
        LocalTime nowTime = LocalTime.now();
        return nowTime.isAfter(SUNRISE_TIME) && nowTime.isBefore(SUNSET_TIME);
    }

    //GETTERS

    public Avatar getAvatar(){
        return this.avatar;
    }

    public Date getCompteur() {
        return this.compteurTemps;
    }

    public boolean getEnCours() {
        return this.enCours;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public FenetrePrincipale getPrincipale(){
        return this.principale;
    }

    //SETTERS


    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
