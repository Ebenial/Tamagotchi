package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;

import java.awt.*;

/**
 * Affiche les règles du jeu
 */
public class Regles extends JPanel {

    public static JButton retour;

    /**
     * Contient le panneau des règles
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Regles(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        retour = new JButton("Retour");
        retour.addActionListener(new ListenerBouton(principale));

        this.add(retour, BorderLayout.SOUTH);
    }
    

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - objet Graphics permettant l'ajout d'une image sur une fenêtre.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/rules/paysage.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
