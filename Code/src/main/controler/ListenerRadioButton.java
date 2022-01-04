package main.controler;

import main.view.FenetrePrincipale;
import main.view.Options;
import main.view.OptionsEnJeu;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Le listener des radio button pour la musique
 */
public class ListenerRadioButton implements ItemListener {

    private FenetrePrincipale principale;

    /**
     * Le constructeur de ListenerRadioButton
     * @param principale
     */
    public ListenerRadioButton(FenetrePrincipale principale){
        this.principale = principale;
    }

    /**
     * Permet de detecter le changement d'etat du boutton
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == Options.r1){
            principale.getBoucle().getClip().start();
        }else if(e.getSource() == Options.r2){
            principale.getBoucle().getClip().stop();
        }
        if(e.getSource() == OptionsEnJeu.r1){
            principale.getBoucle().getClip().start();
        }else if(e.getSource() == OptionsEnJeu.r2){
            principale.getBoucle().getClip().stop();
        }
    }
}
