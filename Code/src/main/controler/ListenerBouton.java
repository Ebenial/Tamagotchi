package main.controler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.util.CustomJButton;
import main.view.*;

/**
 * Contient toutes les actions des boutons
 */
public class ListenerBouton implements ActionListener{

    private FenetrePrincipale principale;
    private String lieu = "Chambre";
    private String direction = "Gauche";
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

    /**else if(e.getSource() == Sauvegardes.sauvegarde1) {
     this.principale.actionChargerPartie(Sauvegardes.sauvegarde1.getText());
     }else if(e.getSource() == Sauvegardes.sauvegarde2) {
     this.principale.actionChargerPartie(Sauvegardes.sauvegarde2.getText());
     }else if(e.getSource() == Sauvegardes.sauvegarde3) {
     this.principale.actionChargerPartie(Sauvegardes.sauvegarde3.getText());
     }
     */

    /**
     * Précise le comportement lorsqu'un bouton est cliqué
     * @param e - l'évènement déclencheur
     */
    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < Sauvegardes.arrayButton.size(); i++) {
            if(e.getSource() == Sauvegardes.arrayButton.get(i)) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                this.principale.actionChargerPartie(Sauvegardes.saveName.get(i));
            }
        }
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
        }else if(e.getSource() == Options.retour) {
            this.principale.actionRetour("Options");
        }else if(e.getSource() == GameOver.retour) {
            this.principale.actionRetour("GameOver");
        }else if(e.getSource() == Options.sauvegarde) {
            this.principale.actionSauvegarde();
        }else if(e.getSource() == Regles.retour){
            this.principale.actionRetour("Regles");
        }else if(e.getSource() == Sauvegardes.retour){
            this.principale.actionRetour("Sauvegardes");
        }else if(e.getSource() == NouvellePartie.valider){
            this.panel.choixAvatar();
            this.principale.actionValider();
        }else if(e.getSource() == NouvellePartie.retour) {
            this.principale.actionRetour("NouvellePartie");
        } else if(e.getSource() == NouvellePartie.choixGauche){
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
            if(this.principale.getJeu().getAvatar().getCanSleep()) {
                int energieActuelle = this.principale.getJeu().getAvatar().getEnergie();
                this.principale.getJeu().getAvatar().setEnergie(energieActuelle + 2);
                this.principale.getJeu().getAvatar().setCanSleep(false);
            }
            else {
                System.out.println("pas encore temps");
            }
        } else if(this.lieu.equals("Douche") && this.direction.equals("Action1")) {
            if(this.principale.getJeu().getAvatar().getCanShower()) {
                int hygieneActuelle = this.principale.getJeu().getAvatar().getHygiene();
                this.principale.getJeu().getAvatar().setHygiene(hygieneActuelle + 2);
                this.principale.getJeu().getAvatar().setCanShower(false);
            }
            else {
                System.out.println("pas encore temps");
            }
        } else if(this.lieu.equals("Cuisine") && this.direction.equals("Action1")) {
            if(this.principale.getJeu().getAvatar().getCanEat()) {
                int mangerActuel = this.principale.getJeu().getAvatar().getNourriture();
                this.principale.getJeu().getAvatar().setNourriture(mangerActuel + 2);
                this.principale.getJeu().getAvatar().setCanEat(false);
            }
            else {
                System.out.println("pas encore temps");
            }
        } else if(this.lieu.equals("Jardin") && this.direction.equals("Action1")) {
            if(this.principale.getJeu().getAvatar().getCanPlay()) {
                int divertissementActuel = this.principale.getJeu().getAvatar().getDivertissement();
                this.principale.getJeu().getAvatar().setDivertissement(divertissementActuel + 2);
                this.principale.getJeu().getAvatar().setCanPlay(false);
            }
            else {
                System.out.println("pas encore temps");
            }
        }

    }
}