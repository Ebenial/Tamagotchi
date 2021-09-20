package main.model;

import main.view.FenetrePrincipale;

/**
 * Classe principale qui dirige la partie
 * S'occupe des statistiques de l'avatar, des évènements ainsi que des conditions de fin de partie
 */
public class Jeu{

    FenetrePrincipale principale;
    private Joueur joueur;
    private Avatar avatar;

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

    public void nourrir(){
        
    }
    
}
