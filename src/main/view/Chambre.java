package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.BoutonFleche;

import java.awt.*;

/**
 * Créé un panneau chambre qui est un des environnements du jeu ainsi que le lieu de départ lors d'une nouvelle partie
 */
public class Chambre extends JPanel {

    public static JButton dormir;
    public static JButton gauche;
    public static JButton droite;
    public static JButton options;
    public JLabel avatarChoisi;
    
    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Chambre(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Affichage des statistiques et de l'heure

        //BORDERLAYOUT.WEST
        //Affichage du bouton fléché gauche pour changer d'environnement
        gauche = new BoutonFleche("Gauche", 150, 120);
        gauche.addActionListener(new ListenerBouton(principale));
        gauche.setSize(new Dimension(600, 150));

        //BORDERLAYOUT.EAST
        //Ajout du bouton fléché droite pour changer d'environnement

        //BORDERLAYOUT.CENTER
        //Affichage de l'avatar ainsi que du JTextfield pour prévenir le joueur des actions à mener

        //BORDERLAYOUT.SOUTH
        //Affichage des actions possibles pour le joueur

        //Ajout des éléments au panneau
        this.add(gauche, BorderLayout.WEST);
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("resources/background/chambre.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
