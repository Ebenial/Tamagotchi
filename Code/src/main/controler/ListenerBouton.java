package main.controler;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import main.model.Lieu;
import main.model.SauvegardePartie;
import main.util.CustomJButton;
import main.util.PopUp;
import main.view.*;

import javax.swing.*;

/**
 * Contient toutes les actions des boutons
 */
public class ListenerBouton implements ActionListener{

    private FenetrePrincipale principale;
    private Lieu lieu;
    private String direction = " ";
    private NouvellePartie panel;

    /**
     * Créé le listener
     * @param fp - FenetrePrincipale qui contient toutes les méthodes
     * @param lieu - l'environnement dans lequel évolue le tamagotchi
     * @param direction - indique la direction de la flèche lors d'un changement de panel
     */
    public ListenerBouton(Lieu lieu, String direction, FenetrePrincipale fp){
        this.principale = fp;
        this.lieu = lieu;
        this.direction = direction;
    }

    /**
     * Créé le listener
     * @param fp - FenetrePrincipale qui contient toutes les méthodes
     * @param lieu - l'environnement dans lequel évolue le tamagotchi
     */
    public ListenerBouton(Lieu lieu, FenetrePrincipale fp){
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

        //JOptionPane.showMessageDialog(null, "principale : " + principale);


            for(int i = 0; i < this.principale.getSauvegardes().getArrayButton().size(); i++) {
                if(e.getSource() == principale.getSauvegardes().getArrayButton().get(i)) {
                    this.principale.actionChargerPartie("./Code/resources/saves/"+principale.getSauvegardes().getSaveName().get(i));
                }
            }

            for(int i = (principale.getSauvegardes().getArrayDelete().size() - 1); i >= 0; i--) {
                if(e.getSource() == principale.getSauvegardes().getArrayDelete().get(i)) {
                    File file = new File("./Code/resources/saves/"+principale.getSauvegardes().getSaveName().get(i));
                    principale.getSauvegardes().getArrayButton().remove(i);
                    principale.getSauvegardes().getSaveName().remove(i);
                    principale.getSauvegardes().getArrayDelete().remove(i);
                    file.delete();
                }
            }

            Sauvegardes save = new Sauvegardes(principale);
            principale.getSauvegardes().setArrayButton(principale.getSauvegardes().getArrayButton());
            principale.getSauvegardes().setArrayDelete(principale.getSauvegardes().getArrayDelete());
            principale.getSauvegardes().setSaveName(principale.getSauvegardes().getSaveName());
            principale.getLayout().removeLayoutComponent(this.principale.getSauvegardes());
            principale.add(save, "sauvegardes");
            principale.setSauvegardes(save);


        ////////////////////////////////////////////////////////
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
        }else if(e.getSource() == NouvellePartie.choixDroite) {
            this.panel.actionSwitchAvatar("Droite");
        } else if (this.direction.equals("Gauche") || this.direction.equals("Droite")) {
            this.principale.actionChangementEnvironnement(this.lieu, this.direction);
        }else if(this.direction.equals("Options")){
            this.principale.actionOptionsEnJeu();
        }else if(this.lieu == Lieu.CHAMBRE && this.direction.equals("Action1")){
            if(this.principale.getJeu().getAvatar().getCanSleep()) {
                int energieActuelle = this.principale.getJeu().getAvatar().getEnergie();
                this.principale.getJeu().getAvatar().setEnergie(energieActuelle + 2);
                this.principale.getJeu().getAvatar().setCanSleep(false);
                this.principale.getCurrentEnvironnement().actionState(false);
            }
            else {
                new PopUp("<html><center>Il est trop tôt pour pouvoir<br> refaire cette action !</html>", this.principale);
            }
        } else if(this.lieu == Lieu.LAVER && this.direction.equals("Action1")) {
            if(this.principale.getJeu().getAvatar().getCanShower()) {
                int hygieneActuelle = this.principale.getJeu().getAvatar().getHygiene();
                this.principale.getJeu().getAvatar().setHygiene(hygieneActuelle + 2);
                this.principale.getJeu().getAvatar().setCanShower(false);
                this.principale.getCurrentEnvironnement().actionState(false);
            }
            else {
                new PopUp("<html><center>Il est trop tôt pour pouvoir<br> refaire cette action !</html>", this.principale);
            }
        } else if(this.lieu == Lieu.MANGER && this.direction.equals("Action1")) {
            if(this.principale.getJeu().getAvatar().getCanEat()) {
                int mangerActuel = this.principale.getJeu().getAvatar().getNourriture();
                this.principale.getJeu().getAvatar().setNourriture(mangerActuel + 2);
                this.principale.getJeu().getAvatar().setCanEat(false);
                this.principale.getCurrentEnvironnement().actionState(false);
            }
            else {
                new PopUp("<html><center>Il est trop tôt pour pouvoir<br> refaire cette action !</html>", this.principale);
            }
        } else if(this.lieu == Lieu.JOUER && this.direction.equals("Action1")) {
            if(this.principale.getJeu().getAvatar().getCanPlay()) {
                int divertissementActuel = this.principale.getJeu().getAvatar().getDivertissement();
                this.principale.getJeu().getAvatar().setDivertissement(divertissementActuel + 2);
                this.principale.getJeu().getAvatar().setCanPlay(false);
                this.principale.getCurrentEnvironnement().actionState(false);
            }
            else {
                new PopUp("<html><center>Il est trop tôt pour pouvoir<br> refaire cette action !</html>", this.principale);
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
            this.principale.getLayout().show(this.principale.getContentPane(), "environnement");
        }else if (e.getSource() == OptionsEnJeu.retourAuMenu){
            this.principale.actionSauvegarde();
            this.principale.actionRetour("OptionsEnJeu");
        }

    }
}