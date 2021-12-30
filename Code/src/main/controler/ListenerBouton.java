package main.controler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import main.view.*;

import javax.swing.*;

/**
 * Contient toutes les actions des boutons
 */
public class ListenerBouton implements ActionListener{

    private FenetrePrincipale principale;
    private String lieu = " ";
    private String direction = " ";
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
        for(int i = 0; i < Sauvegardes.arrayButton.size(); i++) {
            if(e.getSource() == Sauvegardes.arrayButton.get(i)) {
                this.principale.actionChargerPartie(Sauvegardes.saveName.get(i));
            }
        }
        for(int i = 0; i < Sauvegardes.arrayDelete.size(); i++) {
            if(e.getSource() == Sauvegardes.arrayDelete.get(i)) {
                File file = new File(Sauvegardes.saveName.get(i));
                Sauvegardes.arrayButton.remove(i);
                Sauvegardes.saveName.remove(i);
                Sauvegardes.arrayDelete.remove(i);
                file.delete();
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
            this.principale.actionDifficulte();
        }else if(e.getSource() == Jouer.continuer){
            this.principale.actionContinuer();
        }else if(e.getSource() == Jouer.retour){
            this.principale.actionRetour("Jouer");
        }else if(e.getSource() == Options.retour) {
            this.principale.actionRetour("Options");
        }else if(e.getSource() == GameOver.retour) {
            this.principale.actionRetour("GameOver");
        }else if(e.getSource() == OptionsEnJeu.sauvegarde) {
            this.principale.actionSauvegarde();
        }else if(e.getSource() == Regles.retour){
            this.principale.actionRetour("Regles");
        }else if(e.getSource() == Sauvegardes.retour){
            this.principale.actionRetour("Sauvegardes");
        }else if(e.getSource() == NouvellePartie.valider){
            if(!principale.isNameValid()) {
                JOptionPane.showMessageDialog(null, "Le nom n est pas valide");
            }
            if(NouvellePartie.nomJoueur.getText().isEmpty() || NouvellePartie.nomAvatar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Merci de remplir les champs");
            }else{
                if(principale.isNameValid()) {
                    this.panel.choixAvatar();
                    this.principale.actionValider();
                } else {
                    JOptionPane.showMessageDialog(null, "Ce nom d'avatar et de joueur existe déjà pour une autre partie");
                }
            }
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
        } else if(e.getSource() == Difficulte.facile) {
            this.principale.getNouvellePartie().setDifficulty("facile");
            this.principale.actionNouvellePartie();
        } else if(e.getSource() == Difficulte.normal) {
            this.principale.getNouvellePartie().setDifficulty("normal");
            this.principale.actionNouvellePartie();
        } else if(e.getSource() == Difficulte.difficile) {
            this.principale.getNouvellePartie().setDifficulty("difficile");
            this.principale.actionNouvellePartie();
        } else if(e.getSource() == Difficulte.legendaire) {
            this.principale.getNouvellePartie().setDifficulty("legendaire");
            this.principale.actionNouvellePartie();
        } else if(e.getSource() == Difficulte.retour) {
            this.principale.actionRetour("Difficulte");
        }else if(e.getSource() == OptionsEnJeu.retour) {
            switch (this.principale.getCurrentEnvironnement().getLieu()) {
                case "Chambre":
                    this.principale.getLayout().show(this.principale.getContentPane(), "chambre");
                    break;
                case "Douche":
                    this.principale.getLayout().show(this.principale.getContentPane(), "douche");
                    break;
                case "Cuisine":
                    this.principale.getLayout().show(this.principale.getContentPane(), "cuisine");
                    break;
                case "Jardin":
                    this.principale.getLayout().show(this.principale.getContentPane(), "jardin");
                    break;
            }
        }else if (e.getSource() == OptionsEnJeu.retourAuMenu){
            this.principale.actionSauvegarde();
            this.principale.actionRetour("OptionsEnJeu");
        }

    }
}