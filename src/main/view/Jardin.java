package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.BoutonFleche;

import java.awt.*;

public class Jardin extends JPanel {
    
    public static JButton dormir;
    public static JButton gauche;
    public static JButton droite;
    public static JButton options;
    public JLabel avatarChoisi;
    
    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Jardin(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Affichage des statistiques et de l'heure

        //BORDERLAYOUT.WEST
        //Affichage du bouton fléché gauche pour changer d'environnement

        //BORDERLAYOUT.EAST
        //Ajout du bouton fléché droite pour changer d'environnement
        droite = new BoutonFleche("Droite", 150, 120);
        droite.addActionListener(new ListenerBouton(principale));
        droite.setSize(new Dimension(600, 150));

        //BORDERLAYOUT.CENTER
        //Affichage de l'avatar ainsi que du JTextfield pour prévenir le joueur des actions à mener

        //BORDERLAYOUT.SOUTH
        //Affichage des actions possibles pour le joueur

        //Ajout des éléments au panneau
        this.add(droite, BorderLayout.EAST);
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("resources/background/jardin.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}