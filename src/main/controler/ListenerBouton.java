package main.controler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.view.FenetrePrincipale;
import main.view.Accueil;
import main.view.Chambre;
import main.view.Jouer;
import main.view.NouvellePartie;
import main.view.Douche;
import main.view.Cuisine;
import main.view.Jardin;
import main.view.Options;
import main.view.Regles;
import main.view.Sauvegardes;

/**
 * Contient toutes les actions des boutons
 */
public class ListenerBouton implements ActionListener{

    private FenetrePrincipale principale;
    private NouvellePartie panel;

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
        }else if(e.getSource() == Chambre.gauche){
            this.principale.actionChangementEnvironnement("Chambre", "Gauche");
        }else if(e.getSource() == Douche.gauche){
            this.principale.actionChangementEnvironnement("Douche", "Gauche");
        }else if(e.getSource() == Douche.droite){
            this.principale.actionChangementEnvironnement("Douche", "Droite");
        }else if(e.getSource() == Cuisine.gauche){
            this.principale.actionChangementEnvironnement("Cuisine", "Gauche");
        }else if(e.getSource() == Cuisine.droite){
            this.principale.actionChangementEnvironnement("Cuisine", "Droite");
        }else if(e.getSource() == Jardin.droite){
            this.principale.actionChangementEnvironnement("Jardin", "Droite");
        }
    }
}