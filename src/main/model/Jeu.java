package main.model;

import java.util.Date;

import main.view.FenetrePrincipale;

/**
 * Classe principale qui dirige la partie
 * S'occupe des statistiques de l'avatar, des évènements ainsi que des conditions de fin de partie
 */
public class Jeu{

    FenetrePrincipale principale;
    private Joueur joueur;
    private Avatar avatar;
    private double compteurTemps;

    /**
     * Constructeur du type de jeu
     * @param nomJoueur - le pseudo du joueur humain
     * @param nomAvatar - le nom que le joueur a donné à son avatar
     * @param typeAvatar - le type d'avatar choisi (chat, chien, ...)
     */
    public Jeu(String nomJoueur, String nomAvatar, String typeAvatar){
        this.joueur = new Joueur(nomJoueur);
        this.avatar = new Avatar(typeAvatar, nomAvatar);
    }

    public void tempsEcoule(){
        Date date = new Date();
        //This method returns the time in millis
        double tempsActuel = date.getTime();

        if(tempsActuel - this.compteurTemps > 120000){  //Si plus de 2 minutes (en ms) se sont écoulées
            this.avatar.modifierFaim(-1);
            this.avatar.modifierFatigue(-1);
            this.avatar.modifierHygiene(-1);
            this.avatar.modifierBesoins(-1);
            this.compteurTemps = tempsActuel;   //On réinitialise le compteur avec le temps actuel
        }

    }

    //GETTERS

    public Avatar getAvatar(){
        return this.avatar;
    }
    
}
