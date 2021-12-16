package main;

import main.model.BoucleJeu;
import main.view.FenetrePrincipale;

/**
 * Lanceur du programme Tamagotchi - Keneil
 * @author ASPYG
 * @version 0.1
 */
public class Lanceur{

    /**
     * Lance le programme
     * @param args - lance les paramètres du String[], pas nécessaire ici
      */
    public static void main(String[] args){
        FenetrePrincipale principale = new FenetrePrincipale();
        new BoucleJeu(principale);
    }
}