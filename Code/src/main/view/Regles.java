package main.view;

import javax.swing.*;

import main.util.CustomFont;
import main.util.CustomJButton;

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

        //BORDERLAYOUT.NORTH
        //Ajout du titre de la page
        JLabel titre = new JLabel("Règles du jeu", SwingConstants.CENTER);
        titre.setFont(CustomFont.customFont50_PLAIN);
        titre.setBorder(BorderFactory.createEmptyBorder(45,0,10,0));
        titre.setForeground(Color.white);

        //BORDERLAYOUT.WEST
        //Ajout d'un panel vide à gauche
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(500, 0));
        gauche.setOpaque(false);

        //BORDERLAYOUT.EAST
        //Ajout d'un panel vide à droite
        JPanel droite = new JPanel();
        droite.setPreferredSize(new Dimension(500,0));
        droite.setOpaque(false);

        //BORDERLAYOUT.CENTER
        //Ajout d'un panel qui va stocker les différents éléments du panneau
        JPanel options = new JPanel();
        options.setLayout(new GridBagLayout());
        options.setBackground(new Color(255, 255, 255, 161));

        new GridBagConstraints();

        //BORDERLAYOUT.SOUTH
        //Ajout du bouton retour pour revenir à la page d'accueil

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);

        retour = new CustomJButton("Retour", principale, null, null, null, null, null);
        retour.setPreferredSize(new Dimension(384, 96));

        JPanel leftBox = new JPanel();
        leftBox.setPreferredSize(new Dimension(500,0));
        JPanel rightBox = new JPanel();
        rightBox.setPreferredSize(new Dimension(500,0));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(50, 0, 50, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        buttonPanel.add(leftBox, constraints);
        constraints.gridx = 1;
        buttonPanel.add(retour, constraints);
        constraints.gridx = 2;
        buttonPanel.add(rightBox, constraints);

        //Ajout des composants au panneau
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(droite, BorderLayout.EAST);
        this.add(options, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - objet Graphics permettant l'ajout d'une image sur une fenêtre.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
