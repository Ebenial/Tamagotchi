package main.controler;

import main.view.FenetrePrincipale;
import main.view.Options;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListenerRadioButton implements ItemListener {

    private FenetrePrincipale principale;

    public ListenerRadioButton(FenetrePrincipale principale){
        this.principale = principale;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == Options.r1){
            principale.getBoucle().getClip().start();
        }else if(e.getSource() == Options.r2){
            principale.getBoucle().getClip().stop();
        }

    }
}
