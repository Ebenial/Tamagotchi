package main.model;

/**
 * Créé un joueur
 */
public class Joueur {

    private String nom;
    //private Avatar[] listeAvatar;

    /**
     * Construit un joueur
     * @param nomJoueur - le nom que le joueur a choisi comme pseudo
     */
    public Joueur(String nomJoueur){
        this.nom = nomJoueur;
    }

    
    //GETTERS

    /**
     * Le getter du nom
     * @return le nom
     */
    public String getNom(){
        return this.nom;
    }
    
}
