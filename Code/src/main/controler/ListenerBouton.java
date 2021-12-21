package main.controler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.view.FenetrePrincipale;
import main.view.Accueil;
import main.view.Jouer;
import main.view.NouvellePartie;
import main.view.Options;
import main.view.Regles;
import main.view.Sauvegardes;

/**
 * Contient toutes les actions des boutons
 */
public class ListenerBouton implements ActionListener{

    private FenetrePrincipale principale;
    private String lieu;
    private String direction;
    private NouvellePartie panel;

    /**
     * Créé le listener
     * @param fp - FenetrePrincipale qui contient toutes les méthodes
     * @param lieu - l'environnement dans lequel évolue le tamagotchi
     * @param direction - indique la direction de la flèche lors d'un changement de panel
     */
    public ListenerBouton(String lieu, String direction, FenetrePrincipale fp){
        this.principale = fp;
        this.lieu = lieu;
        this.direction = direction;
    }

    /**
     * Créé le listener
     * @param fp - FenetrePrincipale qui contient toutes les méthodes
     * @param lieu - l'environnement dans lequel évolue le tamagotchi
     */
    public ListenerBouton(String lieu, FenetrePrincipale fp){
        this.principale = fp;
        this.lieu = lieu;
    }

    /**
     * Créé le listener
     * @param fp - FenetrePrincipale qui contient toutes les méthodes
     */
    public ListenerBouton(FenetrePrincipale fp){
        this.principale = fp;
    }

    /**
     * Créé le listener
     * @param panel - le panneau dans lequel va s'exécuter l'action demandée
     */
    public ListenerBouton(NouvellePartie panel){
        this.panel = panel;
    }

    /**
     * Créé le listener
     * @param panel - le panneau dans lequel va s'exécuter l'action demandée
     */
    public ListenerBouton(FenetrePrincipale fp, NouvellePartie panel){
        this.principale = fp;
        this.panel = panel;
    }

    /**
     * Précise le comportement lorsqu'un bouton est cliqué
     * @param e - l'évènement déclencheur
     */
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == Accueil.quitter){
            this.principale.actionQuitter();
        }else if(e.getSource() == Accueil.jouer){
            this.principale.actionJouer();
        }else if(e.getSource() == Accueil.regles){
            this.principale.actionRegles();
        }else if(e.getSource() == Accueil.options){
            this.principale.actionOptions();
        }else if(e.getSource() == Jouer.nouvellePartie){
            this.principale.actionNouvellePartie();
        }else if(e.getSource() == Jouer.continuer){
            this.principale.actionContinuer();
        }else if(e.getSource() == Jouer.retour){
            this.principale.actionRetour("Jouer");
        }else if(e.getSource() == Options.retour){
            this.principale.actionRetour("Options");
        }else if(e.getSource() == Regles.retour){
            this.principale.actionRetour("Regles");
        }else if(e.getSource() == Sauvegardes.retour){
            this.principale.actionRetour("Sauvegardes");
        }else if(e.getSource() == NouvellePartie.valider){
            this.panel.choixAvatar();
            this.principale.actionValider();
        }else if(e.getSource() == NouvellePartie.retour){
            this.principale.actionRetour("NouvellePartie");
        }else if(e.getSource() == NouvellePartie.choixGauche){
            this.panel.actionSwitchAvatar("Gauche");
        }else if(e.getSource() == NouvellePartie.choixDroite){
            this.panel.actionSwitchAvatar("Droite");
        }else if(this.lieu.equals("Chambre") && this.direction.equals("Gauche")){
            this.principale.actionChangementEnvironnement("Chambre", "Gauche");
        }else if(this.lieu.equals("Douche") && this.direction.equals("Gauche")){
            this.principale.actionChangementEnvironnement("Douche", "Gauche");
        }else if(this.lieu.equals("Douche") && this.direction.equals("Droite")){
            this.principale.actionChangementEnvironnement("Douche", "Droite");
        }else if(this.lieu.equals("Cuisine") && this.direction.equals("Gauche")){
            this.principale.actionChangementEnvironnement("Cuisine", "Gauche");
        }else if(this.lieu.equals("Cuisine") && this.direction.equals("Droite")){
            this.principale.actionChangementEnvironnement("Cuisine", "Droite");
        }else if(this.lieu.equals("Jardin") && this.direction.equals("Droite")){
            this.principale.actionChangementEnvironnement("Jardin", "Droite");
        }else if(this.direction.equals("Options")){
            this.principale.actionOptionsEnJeu();
        }else if(this.lieu.equals("Chambre") && this.direction.equals("Action1")){
            //TODO : enlever le print (jsp si ça sert à quelqu'un ou pas)

            System.out.println("coucou");
            int santeActuelle = this.principale.getJeu().getAvatar().getSante();
            this.principale.getJeu().getAvatar().setSante(santeActuelle + 2);
        }
    }
}