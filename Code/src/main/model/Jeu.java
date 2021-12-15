package main.model;

import java.util.Date;

import javax.swing.ImageIcon;

import java.awt.Image;

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

    /**
     * Constructeur du type de jeu
     * @param nomJoueur - le pseudo du joueur humain
     * @param nomAvatar - le nom que le joueur a donné à son avatar
     * @param typeAvatar - le type d'avatar choisi (chat, chien, ...)
     */
    public Jeu(String nomJoueur, String nomAvatar, String typeAvatar){
        this.joueur = new Joueur(nomJoueur);
        this.avatar = new Avatar(typeAvatar, nomAvatar);
        init();
    }

    /**
     * Compare le temps écoulé depuis le dernier rafraichissement des caractéristiques. Modifie les caractéristiques toutes les 2 minutes 
     */
    public void tempsEcoule(){
        
        Date date = new Date();

        if((date.getTime() - this.compteurTemps.getTime()) / 1000 > 2){  //Si plus de 2 minutes (en ms) se sont écoulées
            System.out.println("compteur temps : " + this.compteurTemps);
            System.out.println("temps actuel : " + date);
            this.avatar.modifierNourriture(-1);
            this.avatar.modifierEnergie(-1);
            this.avatar.modifierHygiene(-1);
            this.avatar.modifierDivertissement(-1);
            this.compteurTemps = date;   //On réinitialise le compteur avec le temps actuel
            System.out.println(this.avatar.getDivertissement());
        }

    }
    // Je fais cette fonction pour tester l'intérieur de la fonction tempsEcoule
    // et eviter d'avoir à vérifier la condition du if qui m'empêche de faire le test sur la méthode tempsEcoule normale
    public void tempsEcoule_test() {
        Date date = new Date();

        this.avatar.modifierNourriture(-1);
        this.avatar.modifierEnergie(-1);
        this.avatar.modifierHygiene(-1);
        this.avatar.modifierDivertissement(-1);
        this.compteurTemps = date;
    }

    /**
     * Initialise la partie 
     */
    private void init(){
        this.enCours = true;
        this.compteurTemps = new Date();

    }

    /**
     * Récupère la valeur de statistique de l'avatar et indique quelle image de barre de statistiques choisir en conséquence
     * @param valeur - la valeur de la statistique
     * @return l'image qui correspond à la valeur
     */
    public ImageIcon choixBarreStats(int valeur){

        ImageIcon res;

        switch(valeur){
            case 10:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques10.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 1:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques01.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 2:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques02.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 3:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques03.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break; 
            case 4:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques04.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 5:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques05.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break; 
            case 6:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques06.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 7:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques07.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break; 
            case 8:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques08.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;
            case 9:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques09.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;   
            default:
                res = new ImageIcon(new ImageIcon("Code/resources/others/barreStatistiques00.png").getImage().getScaledInstance(300, 30, Image.SCALE_DEFAULT));
                break;

        }

        return res;
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


    
}
