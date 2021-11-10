package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;

import java.awt.*;

public class Sauvegardes extends JPanel {

    public static JButton retour;

    /**
     * Contient le panneau des règles
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Sauvegardes(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        retour = new JButton("Retour");
        retour.addActionListener(new ListenerBouton(principale));

        this.add(retour, BorderLayout.SOUTH);
    }
    

        /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/sauvegardes.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}